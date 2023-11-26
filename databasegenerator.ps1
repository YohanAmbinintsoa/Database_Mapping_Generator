param (
    [string]$class
)

javac -d . *.java
java Main.Main $class