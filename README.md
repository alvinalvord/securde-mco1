# securde mco1

# To do:
- [x] on login, password must be censored
- [x] on login, username and password field must reset
- [ ] on login, user must be authenticated
- [ ] on login, user and password must not be null
- [ ] on login, there should be feedback on login failure
- [x] on register, password and confirm must be censored
- [x] on register, username and password field must reset
- [x] on register, password must be verified
- [x] on register, user must append to database
- [x] on register, there should be feedback on register failure/success
- [x] on register, username must be case insensitive
- [x] on register, username and password must not be null
- [x] on register, username must be unique
- [x] on register, errors must be logged
- [ ] on access, user should not access all roles
- [ ] on database, password should be encrypted
- [ ] on database operation, logs must be recorded
- [ ] on database failure, program must close
- [ ] on exception, errors must be reported

# list of changes:
Security Issue | Java file(s) modified | possible vulnerability | solution
-|-|-|-
password fields are not censored | Login, Register | ? | changed JTextField to JPasswordField