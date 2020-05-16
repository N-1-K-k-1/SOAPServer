package ru.n1kk1.kotlinSOAP.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "customer")
class CustomerEntity : Serializable {
        @Column(name = "firstName", length = 200)
        var firstName: String? = null

        @Column(name = "lastName", length = 1000)
        var lastName: String? = null

        @Column(name = "salary")
        var salary: Long = 0

        @Column(name = "city", length = 1000)
        var city: String? = null

        @Column(name = "country", length = 200)
        var country: String? = null

        @Column(name = "email", length = 1000)
        var email: String? = null

        @Id
        @Column(name = "id")
        var id: Long = 0

        constructor() {}
        constructor(id: Long, firstName: String?, lastName: String?, salary: Long, city: String?, country: String?, email: String?) {
                this.id = id
                this.firstName = firstName
                this.lastName = lastName
                this.salary = salary
                this.city = city
                this.country = country
                this.email = email
        }

        fun setCustomerId(id: Long) {
                this.id = id
        }

        fun setCustomersFirstName(firstName: String) {
                this.firstName = firstName
        }

        fun setCustomersLastName(lastName: String) {
                this.lastName = lastName
        }

        fun setCustomersSalary(salary: Long) {
                this.salary = salary
        }

        fun setCustomersCity(city: String) {
                this.city = city
        }

        fun setCustomersCountry(country: String) {
                this.country = country
        }

        fun setCustomersEmail(email: String) {
                this.email = email
        }

        companion object {
                private const val serialVersionUID: Long = 1L
        }
}