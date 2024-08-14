package scalabridge.nonpure

import scalabridge.Validated

trait ValidatedBuilder[A <: Validated[A]] {
  def build(value: A): A = {
    value.getValid() match {
      case Left(exceptions) => throw exceptions.head
      case Right(value)     => value
    }
  }
}
