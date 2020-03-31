package ru.n1kk1.kotlinSOAP.service

import ru.n1kk1.kotlinSOAP.entity.CustomerEntity

interface CustomerService {
    fun getCustomerById(id: Long): CustomerEntity?
    fun getCustomerByFirstName(firstName: String?): CustomerEntity?
    fun getCustomerBySecondName(secondName: String?): CustomerEntity?
    fun getAllCustomers(): List<CustomerEntity?>?
    fun addCustomer(customer: CustomerEntity?): CustomerEntity?
    fun updateCustomer(customer: CustomerEntity?): Boolean
    fun deleteCustomer(id: Long): Boolean
}
