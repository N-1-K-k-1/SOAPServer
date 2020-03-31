package ru.n1kk1.kotlinSOAP.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.n1kk1.kotlinSOAP.entity.CustomerEntity
import ru.n1kk1.kotlinSOAP.repository.CustomerRepository
import javax.transaction.Transactional

@Service
@Transactional
class CustomerServiceImpl : CustomerService {

    var repository: CustomerRepository? = null

    fun CustomerServiceImpl() {}

    @Autowired
    fun CustomerServiceImpl(repository: CustomerRepository?){
        this.repository = repository
    }

    @Override
    override fun getCustomerById(id: Long): CustomerEntity? {
        return this.repository?.findById(id)?.get()
    }

    @Override
    override fun getCustomerByFirstName(firstName: String?): CustomerEntity? {
        return this.repository?.findByFirstName(firstName)
    }

    @Override
    override fun getCustomerBySecondName(secondName: String?): CustomerEntity? {
        return this.repository?.findBySecondName(secondName)
    }

    @Override
    override fun getAllCustomers(): List<CustomerEntity?> {
        val list: ArrayList<CustomerEntity> = ArrayList <CustomerEntity> ()
        repository?.findAll()?.forEach { e ->
            if (e != null) {
                list.add(e)
            }
        }
        return list
    }

    @Override
    override fun addCustomer(customer: CustomerEntity?): CustomerEntity? {
        return try {
            this.repository?.save(customer!!)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }

    @Override
    override fun updateCustomer(customer: CustomerEntity?): Boolean {
        return try {
            this.repository?.save(customer!!)
            true
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            false
        }
    }

    @Override
    override fun deleteCustomer(id: Long): Boolean {
        try {
            this.repository?.deleteById(id)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

}
