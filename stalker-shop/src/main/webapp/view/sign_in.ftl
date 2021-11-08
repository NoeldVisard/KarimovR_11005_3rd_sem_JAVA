<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign in</title>
    <link rel="icon" type="image/x-icon" href="/resources/images/design/icon_stalker.ico"/>
    <link rel="stylesheet" href="../resources/css/base.css">
    <link rel="stylesheet" href="../resources/css/sign_form.css">
</head>
<body>
<#include "header_base.ftl">
<div class="wrapper">
    <section class="section-form">
        <div class="form-container">
            <p class="form-header text-header">Sign in</p>
            <form class="form-sign" method="post">
                <label>
                    <input class="form-sign-email" placeholder="Email" name="email" type="email">
                </label>
                <label>
                    <input class="form-sign-password" placeholder="Password" name="password" type="password">
                </label>
                <input class="form-sign-button-submit" value="Enter" type="submit">
            </form>
            <button class="form-button-secondary" onclick="window.location.href='sign-up'">Register</button>
        </div>
    </section>
</div>
</body>
</html>