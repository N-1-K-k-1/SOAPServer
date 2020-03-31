package ru.n1kk1.kotlinSOAP.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import ru.n1kk1.kotlinSOAP.entity.CustomerEntity

@Repository
interface CustomerRepository : CrudRepository<CustomerEntity?, Long?> {
    fun findByFirstName(firstName: String?): CustomerEntity?
    fun findBySecondName(secondName: String?): CustomerEntity?
}
