package ru.n1kk1.kotlinSOAP.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.n1kk1.kotlinSOAP.entity.CustomerEntity
import ru.n1kk1.kotlinSOAP.repository.CustomerRepository
import java.io.IOException
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
    override fun getCustomerByLastName(lastName: String?): CustomerEntity? {
        return this.repository?.findByLastName(lastName)
    }

    @Override
    override fun getAllCustomers(): List<CustomerEntity?> {
        val list: ArrayList<CustomerEntity> = ArrayList()
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
        return try {
            this.repository?.deleteById(id)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    @Override
    override fun downloadImage(name: String): ByteArray? {
        val `in` = javaClass.getResourceAsStream("/$name")
        try {
            return `in`.readAllBytes()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

}
