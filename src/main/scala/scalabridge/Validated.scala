package scalabridge

trait Validated[A] {
  def getValid(): Either[Iterable[Throwable], A]
}
