package club.libridge.libridgebackend.core.nonpure

import club.libridge.libridgebackend.core.Validated

trait ValidatedBuilder[A<:Validated[A]] {
    def build(value:A): A = {
        value.getValid() match {
            case Left(exceptions) => throw exceptions.head
            case Right(value) => value
        }
    }
}
