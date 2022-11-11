package com.intern.accountservice.authentication.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import javax.persistence.*


@Entity
@Table(name = "degrees")
data class Degree (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = 0,

    @Column(name = "namee")
    var name: String? = "",

    @Column(name = "place_of_issue")
    var placeOfIssue: String? = "",

    @Column(name = "date_of_issue")
    var dateOfIssue: String? = "",

    @Column(name = "date_of_expiry")
    var dateOfExpiry: String? = "",

    @Column(name = "status")
    var status: Int? = 0,

    @Column(name = "img1")
    var img1: String? = null,

    @Column(name = "img2")
    var img2: String? = null,

    @Column(name = "img3")
    var img3: String? = null,

    @Column(name = "img4")
    var img4: String? = null,

    @Column(name = "img5")
    var img5: String? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore var user: User?

)