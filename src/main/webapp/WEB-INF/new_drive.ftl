<html>
<head>
    <title>Neue Fahrt</title>

    <style>
        body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 64px 32px;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            align-items: center;
            font-family: Arial;
        }

        body table {
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

        body table tbody {
            width: 100%;
        }

        body table tr {
            width: 100%;
            margin: 8px 0;
            display: grid;
            grid-gap: 16px;
            grid-template-columns: repeat(2, 1fr);
        }

        body table tr td {
            width: 100%;
            display: flex;
            flex: 1;
            flex-direction: row;
            position: relative;
        }

        body table tr td:last-child {
            align-items: flex-start;
        }

        body table tr td input.large[type=text] {
            width: 100%;
            height: 28px;
        }

        body table tr td input.small[type=text] {
            width: 64px;
            height: 28px;
            margin: 0 auto;
        }

        body table tr td .euro {
            position: absolute;
            top: 0;
            left: calc(50% + 48px);
            transform: translate(-50%, -50%);
        }

        body table tr td textarea {
            width: 100%;
            min-height: 128px;
            max-height: 256px;
            resize: vertical;
        }

        body table tr td label {
            margin-right: 16px;
        }

        body table tr td button {
            font-size: 1.1em;
            font-weight: bold;
            margin-top: 32px;
            margin-left: auto;
            padding: 8px 16px;
            text-decoration: none;
            color: white;
            background: #2C5B9C;
            box-shadow: 0 8px 24px -16px rgba(0, 0, 0, .4);
            box-sizing: border-box;
            outline: none;
            border: none;
            transition: all 250ms ease;
            cursor: pointer;
        }

        body table tr td button:hover {
            opacity: 0.8;
        }

        body table tr td button:active {
            background: #214579;
        }
    </style>
</head>
<body>
    <h1>Fahrt erstellen</h1>

    <table>
        <tr>
            <td><b>Von:</b></td>
            <td>
                <input class="large" type="text" placeholder="Stadt eingeben...">
            </td>
        </tr>
        <tr>
            <td><b>Bis:</b></td>
            <td>
                <input class="large" type="text" placeholder="Stadt eingeben...">
            </td>
        </tr>
        <tr>
            <td><b>Maximale Kapazität:</b></td>
            <td>
                <input class="small" type="text" placeholder="1-10">
            </td>
        </tr>
        <tr>
            <td><b>Fahrtkosten:</b></td>
            <td>
                <input class="small" type="text" placeholder="Betrag">
                <p class="euro">€</p>
            </td>
        </tr>
        <tr>
            <td><b>Transportmittel:</b></td>
            <td>
                <input type="radio" name="cartype" id="car" value="car" checked>
                <label for="car">Auto</label>

                <input type="radio" name="cartype" id="bus" value="bus">
                <label for="bus">Bus</label>

                <input type="radio" name="cartype" id="transporter" value="transporter">
                <label for="transporter">Kleintransporter</label>
            </td>
        </tr>
        <tr>
            <td><b>Fahrtdatum:</b></td>
            <td>
                Platzhalter
            </td>
        </tr>
        <tr>
            <td><b>Beschreibung:</b></td>
            <td>
                <textarea></textarea>
            </td>
        </tr>

        <tr>
            <td></td>
            <td>
                <button>Erstellen</button>
            </td>
        </tr>
    </table>
</body>
</html>