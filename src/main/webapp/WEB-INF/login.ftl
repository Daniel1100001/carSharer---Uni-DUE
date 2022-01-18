<html>
<head>
    <title>Login</title>

    <style>
        body {
            margin: 0;
            padding: 64px 32px;
            font-family: Arial;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        body h1 {
            margin-bottom: 32px;
        }

        body .content {
            width: 100%;
            max-width: 768px;
            padding: 32px;
            display: flex;
            flex-direction: column;
            align-items: center;
            background: rgba(0, 0, 0, .0625);
            border-radius: 12px;
            box-shadow: 0 8px 24px -16px rgba(0, 0, 0, .4);
            box-sizing: border-box;
        }

        body .content table {
            margin-bottom: 32px;
        }

        body .content table tr {
            margin: 8px 8px;
            display: grid;
            grid-template-columns: 1fr 3fr;
            grid-gap: 16px;
        }

        body .content table tr td:first-child {
            font-weight: bold;
        }

        body .content button {
            padding: 5px 15px;
            color: white;
            font-size: 1.1em;
            font-weight: bold;
            background: #2C5B9C;
            border: none;
            cursor: pointer;
            box-sizing: border-box;
            transition: all 250ms ease;
        }

        body .content button:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <h1>Login - Benutzer</h1>

    <div class="content">
        <table>
            <tr>
                <td>Name:</td>
                <td><input required type="text" placeholder="Name eingeben..."></td>
            </tr>
            <tr>
                <td>E-Mail:</td>
                <td><input required type="email" placeholder="E-Mail eingeben..."></td>
            </tr>
        </table>

        <button type="submit">Login</button>
    </div>
</body>
</html>