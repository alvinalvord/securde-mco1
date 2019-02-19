# securde mco1

# To do:
- [x] on login, password must be censored
- [x] on login, username and password field must reset
- [x] on login, user must be authenticated
- [x] on login, user and password must not be null
- [x] on login, there should be feedback on login failure
- [x] on register, password and confirm must be censored
- [x] on register, username and password field must reset
- [x] on register, password must be verified
- [x] on register, user must append to database
- [x] on register, there should be feedback on register failure/success
- [x] on register, username must be case insensitive
- [x] on register, username and password must not be null
- [x] on register, username must be unique
- [x] on register, errors must be logged
- [x] on access, user should not access all roles
- [x] on database, password should be encrypted
- [x] on database operation, logs must be recorded
- [x] on database failure, program must close
- [x] on exception, errors must be reported
- [x] on logout, user must reset
- [x] on database, table must exist before dropping

# list of changes:
Security Issue | Java file(s) modified | Possible Vulnerability | Solution
-|-|-|-
password fields are not censored | Login, Register | password can be seen by other people | changed JTextField to JPasswordField
username and password fields do not reset | Login, Register | another user can login using the previous user's credentials | fields are reset to empty string when changing views
password is not encrypted | SQLite, PasswordEncryption | password can be queried directly from database | passwords are hashed using SHA-512 before storing into database