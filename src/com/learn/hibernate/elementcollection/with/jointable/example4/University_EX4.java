package com.learn.hibernate.elementcollection.with.jointable.example4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * @author EI08087
 *
 */
@Entity(name = "UNIVERSITY_EX4")
@Table(name = "UNIVERSITY_EX4")
public class University_EX4 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "U_ID")
    private Integer   id;
    @Column(name = "U_NAME")
    private String    name;

    /*
     * if only @ElementCollection annotation is being used then default configuration applies
     * to table name and foreign key name like below
     * insert into University_deptList (University_U_ID, deptBlock, deptName) values (?, ?, ?)
     * 
     * @ElementCollection
     * private Set<Dept> deptList = new HashSet();
     */

    @ElementCollection
    // It will rename the Collection table name and foreign key name
    @JoinTable(name = "UNIVERSITY_DEPT", joinColumns = @JoinColumn(name = "university_id"))
    // describes the generator to define the sequence of primary key in this case used "increment" strategy generator
    @GenericGenerator(name = "increment-gen", strategy = "increment")
    // For a collection represent the unique id(foreign key) column with generator (increment strategy)
    @CollectionId(columns = { @Column(name = "dept_id") }, generator = "increment-gen", type = @Type(type = "long"))
    private Collection<Dept_EX4> deptList = new ArrayList();

    public University_EX4() {
    }
    public University_EX4(int id, String name, Set<Dept_EX4> deptList) {
        this.id = id;
        this.name = name;
        this.deptList = deptList;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Dept_EX4> getDeptList() {
        return deptList;
    }

    public void setDeptList(Collection<Dept_EX4> deptList) {
        this.deptList = deptList;
    }


}
