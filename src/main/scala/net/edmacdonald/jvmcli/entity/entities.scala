package net.edmacdonald.jvmcli.entity

import jakarta.persistence.*

import java.math.BigInteger
import java.util
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

  @BeanProperty
  @OneToMany(mappedBy = "commandInvocation", cascade = Array(CascadeType.ALL), orphanRemoval = true)
  var args: java.util.List[CommandArgument] = new util.ArrayList()

  @BeanProperty
  @OneToMany(mappedBy = "commandInvocation", cascade = Array(CascadeType.ALL), orphanRemoval = true)
  var resultLines: java.util.List[ResultLine] = new util.ArrayList()

  def addArg(arg: CommandArgument): Unit = {
    arg.commandInvocation = this
    args.add(arg)
  }
}

@Entity
class CommandArgument {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @BeanProperty
  var id: BigInteger | Null = null

  @BeanProperty
  @ManyToOne
  @JoinColumn(name = "command_invocation_id")
  var commandInvocation: CommandInvocation = uninitialized

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
  @ManyToOne
  @JoinColumn(name = "command_invocation_id")
  var commandInvocation: CommandInvocation = uninitialized

  @BeanProperty
  var lineOrder: BigInteger = uninitialized

  @BeanProperty
  var line: String = uninitialized
}
