package ru.n1kk1.kotlinSOAP.endpoint

import io.spring.guides.gs_producing_web_service.*
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload
import ru.n1kk1.kotlinSOAP.entity.CustomerEntity
import ru.n1kk1.kotlinSOAP.service.CustomerService

@Endpoint
class CustomerEndpoint {

    private var service: CustomerService? = null

    constructor() {}
    @Autowired
    constructor(service: CustomerService?) {
        this.service = service
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCustomerByIdRequest")
    @ResponsePayload
    fun getCustomerById(@RequestPayload request: GetCustomerByIdRequest): GetCustomerByIdResponse? {
        val response = GetCustomerByIdResponse()
        val customerEntity: CustomerEntity? = service?.getCustomerById(request.getId())
        val customer = Customer()
        BeanUtils.copyProperties(customerEntity!!, customer)
        response.setCustomer(customer)
        return response
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllMoviesRequest")
    @ResponsePayload
    fun getAllCustomers(@RequestPayload request: GetAllCustomersRequest?): GetAllCustomersResponse? {
        val response = GetAllCustomersResponse()
        val customerList: MutableList<Customer> = java.util.ArrayList()
        val customerEntityList: List<CustomerEntity?>? = service?.getAllCustomers()
        for (entity in customerEntityList!!) {
            val customer = Customer()
            BeanUtils.copyProperties(entity!!, customer)
            customerList.add(customer)
        }
        response.getCustomer().addAll(customerList)
        return response
    }

    @PayloadRoot(namespace = CustomerEndpoint.NAMESPACE_URI, localPart = "addCustomerRequest")
    @ResponsePayload
    fun addMovie(@RequestPayload request: AddCustomerRequest): AddCustomerResponse? {
        val response = AddCustomerResponse()
        val newCustomer = Customer()
        val serviceStatus = ServiceStatus()
        val newCustomerEntity = CustomerEntity(request.getFirstName(), request.getLastName(), request.getSalary(),
                request.getCity(), request.getCountry(), request.getEmail())
        val savedCustomerEntity: CustomerEntity? = service?.addCustomer(newCustomerEntity)
        if (savedCustomerEntity == null) {
            serviceStatus.statusCode = "CONFLICT"
            serviceStatus.message = "Exception while adding Entity"
        } else {
            BeanUtils.copyProperties(savedCustomerEntity, newCustomer)
            serviceStatus.statusCode = "SUCCESS"
            serviceStatus.message = "Content Added Successfully"
        }
        response.setCustomer(newCustomer)
        response.setServiceStatus(serviceStatus)
        return response
    }

    @PayloadRoot(namespace = CustomerEndpoint.NAMESPACE_URI, localPart = "updateCustomerRequest")
    @ResponsePayload
    fun updateCustomer(@RequestPayload request: UpdateCustomerRequest): UpdateCustomerResponse? {
        val response = UpdateCustomerResponse()
        val serviceStatus = ServiceStatus()
        // 1. Find if customer available
        val customerFromDB: CustomerEntity? = service?.getCustomerBySecondName(request.getLastName())
        if (customerFromDB == null) {
            serviceStatus.statusCode = "NOT FOUND"
            serviceStatus.message = "Customer with '" + request.getLastName().toString() + "' last name not found"
        } else { // 2. Get updated customer information from the request
            customerFromDB.setCustomersFirstName(request.getFirstName())
            customerFromDB.setCustomersSecondName(request.getLastName())
            customerFromDB.setCustomersSalary(request.getSalary())
            customerFromDB.setCustomersCity(request.getCity())
            customerFromDB.setCustomersCountry(request.getCountry())
            customerFromDB.setCustomersEmail(request.getEmail())
            // 3. update the movie in database
            val flag: Boolean? = service?.updateCustomer(customerFromDB)
            if (flag == null) {
                serviceStatus.statusCode = "CONFLICT"
                serviceStatus.message = "Exception while updating Entity=" + request.getFirstName() + request.getLastName()
            } else {
                serviceStatus.statusCode = "SUCCESS"
                serviceStatus.message = "Content updated Successfully"
            }
        }
        response.setServiceStatus(serviceStatus)
        return response
    }

    @PayloadRoot(namespace = CustomerEndpoint.NAMESPACE_URI, localPart = "deleteCustomerRequest")
    @ResponsePayload
    fun deleteCustomer(@RequestPayload request: DeleteCustomerRequest): DeleteCustomerResponse? {
        val response = DeleteCustomerResponse()
        val serviceStatus = ServiceStatus()
        val flag: Boolean? = service?.deleteCustomer(request.getId())
        if (flag == null) {
            serviceStatus.statusCode = "FAIL"
            serviceStatus.message = "Exception while deleting Entity id=" + request.getId()
        } else {
            serviceStatus.statusCode = "SUCCESS"
            serviceStatus.message = "Content Deleted Successfully"
        }
        response.setServiceStatus(serviceStatus)
        return response
    }

    companion object {
        const val NAMESPACE_URI: String = "http://spring.io/guides/gs-producing-web-service"
    }
}