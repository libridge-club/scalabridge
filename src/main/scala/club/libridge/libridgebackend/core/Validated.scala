package club.libridge.libridgebackend.core

trait Validated[A] {
    def getValid(): Either[ Iterable[Exception], A ]
}