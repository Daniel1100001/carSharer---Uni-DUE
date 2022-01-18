<html>
<head>
    <title>Fahrt Suche</title>

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

        body .content table.header {
            width: 100%;
            margin-bottom: 32px;
            padding-bottom: 32px;
            border-bottom: 1px solid rgba(0, 0, 0, .4);
            box-sizing: border-box;
        }

        body .content table.header tr {
            width: 100%;
            margin: 8px auto;
            display: grid;
            grid-gap: 16px;
            grid-template-columns: 1fr 1fr;
        }

        body .content table.header tr td b {
            width: 64px;
            display: inline-block;
        }

        body .content table.header tr td {
            display: flex;
            flex-direction: row;
            align-items: center;
        }

        body .content table.header tr td input {
            margin-right: 16px;
        }
        body .content table.header tr td button {
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

        body .content table.header tr td button:hover {
            opacity: 0.8;
        }

        body .content .results {
            width: 100%;
            display: flex;
            flex-direction: column;
        }

        body .content .results h2 {
            margin: 0 0 32px 0;
            padding: 0;
        }

        body .content .results .drives-list {
            display: grid;
            grid-gap: 16px;
            grid-template-columns: repeat(3, minmax(0, 1fr));
        }

        body .content .results .drives-list .drive-card {
            width: 100%;
            padding: 16px;
            display: flex;
            flex-direction: column;
            align-items: center;
            border-radius: 10px;
            border: 1px solid rgba(0, 0, 0, .25);
            box-sizing: border-box;
        }

        body .content .results .drives-list .drive-card table {
            width: 100%;
        }

        body .content .results .drives-list .drive-card table tr {
            width: 100%;
            margin: 4px 0;
            display: grid;
            grid-gap: 16px;
            grid-template-columns: 1fr 2fr;
        }

        body .content .results .drives-list .drive-card table tr td {
            text-align: right;
        }

        body .content .results .drives-list .drive-card table tr td:first-child {
            font-weight: bold;
        }

        body .content .results .drives-list .drive-card button {
            width: 3em;
            height: 3em;
            margin-bottom: 16px;
        }
    </style>
</head>
<body>
    <h1>Fahrt suchen</h1>

    <div class="content">
        <table class="header">
            <tr>
                <td><b>Start:</b><input required type="text" placeholder="Stadt eingeben..."></td>
                <td><b>Ziel:</b><input required type="text" placeholder="Stadt eingeben..."></td>
            </tr>
            <tr>
                <td><b>Ab:</b><input required type="date">Datum</td>
                <td><button type="submit">Suche</button></td>
            </tr>
        </table>

        <div class="results">
            <h2>Suchergebnisse</h2>

            <div class="drives-list">
                <div class="drive-card">
                    <button>X</button>
                    <table>
                        <tr>
                            <td>Von:</td>
                            <td>Berliner Dom</td>
                        </tr>
                        <tr>
                            <td>Nach:</td>
                            <td>Essen-Süd</td>
                        </tr>
                        <tr>
                            <td>Preis:</td>
                            <td>40 €</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>