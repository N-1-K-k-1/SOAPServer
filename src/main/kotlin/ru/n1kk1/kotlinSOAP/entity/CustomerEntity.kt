package ru.n1kk1.kotlinSOAP.entity

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "customer")
class CustomerEntity : Serializable {
        @Column(name = "firstName", length = 200)
        var firstName: String? = null

        @Column(name = "secondName", length = 1000)
        var secondName: String? = null

        @Column(name = "salary")
        var salary: Long = 0

        @Column(name = "city", length = 200)
        var city: String? = null

        @Column(name = "country", length = 1000)
        var country: String? = null

        @Column(name = "email", length = 1000)
        var email: String? = null

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0

        constructor() {}
        constructor(firstName: String?, secondName: String?, salary: Long, city: String?, country: String?, email: String?) {
                this.firstName = firstName
                this.secondName = secondName
                this.salary = salary
                this.city = city
                this.country = country
                this.email = email
        }

        fun getCustomerId(): Long {
                return id
        }

        fun setCustomerId(id: Long) {
                this.id = id
        }

        fun getCustomersFirstName(): String? {
                return firstName
        }

        fun setCustomersFirstName(firstName: String) {
                this.firstName = firstName
        }

        fun getCustomersSecondName(): String? {
                return secondName
        }

        fun setCustomersSecondName(secondName: String) {
                this.secondName = secondName
        }

        fun getCustomersSalary(): Long {
                return salary
        }

        fun setCustomersSalary(salary: Long) {
                this.salary = salary
        }

        fun getCustomersCity(): String? {
                return city
        }

        fun setCustomersCity(city: String) {
                this.city = city
        }

        fun getCustomersCountry(): String? {
                return country
        }

        fun setCustomersCountry(country: String) {
                this.country = country
        }

        fun getCustomersEmail(): String? {
                return email
        }

        fun setCustomersEmail(email: String) {
                this.email = email
        }

        companion object {
                private const val serialVersionUID: Long = 1L
        }
}