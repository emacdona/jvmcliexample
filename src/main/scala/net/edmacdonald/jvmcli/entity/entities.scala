package net.edmacdonald.jvmcli.entity

import jakarta.persistence.*

import java.math.BigInteger
import scala.beans.BeanProperty
import scala.compiletime.uninitialized

@Entity
class CommandInvocation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @BeanProperty
  var id: BigInteger | Null = null

  @BeanProperty
  var command: String = uninitialized

  @OneToMany(mappedBy = "commandInvocationId", cascade = Array(CascadeType.ALL))
  @BeanProperty
  var args: java.util.List[CommandArgument] = uninitialized

  @OneToMany(mappedBy = "commandInvocationId", cascade = Array(CascadeType.ALL))
  @BeanProperty
  var resultLines: java.util.List[ResultLine] = uninitialized
}

@Entity
class CommandArgument {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @BeanProperty
  var id: BigInteger | Null = null

  @BeanProperty
  var commandInvocationId: Int = uninitialized

  @BeanProperty
  var argumentOrder: BigInteger = uninitialized

  @BeanProperty
  var arg: String = uninitialized
}

@Entity
class ResultLine {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @BeanProperty
  var id: BigInteger | Null = null

  @BeanProperty
  var commandInvocationId: BigInteger = uninitialized

  @BeanProperty
  var lineOrder: BigInteger = uninitialized

  @BeanProperty
  var line: String = uninitialized
}
