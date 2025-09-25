package net.edmacdonald.jvmcli.repository

import net.edmacdonald.jvmcli.entity.CommandInvocation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.ListCrudRepository

import java.math.BigInteger


trait CommandInvocationRepository extends JpaRepository[CommandInvocation, Long], ListCrudRepository[CommandInvocation, Long] {
  def findById(id: BigInteger): CommandInvocation;
  def findAll(): java.util.List[CommandInvocation];
}
