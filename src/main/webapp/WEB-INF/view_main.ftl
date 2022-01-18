<html>
<head>
    <title>Hauptseite</title>

    <style>
        body {
            width: 100%;
            margin: 0;
            padding: 64px 32px;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            align-items: center;
            font-family: Arial;
        }

        body .content {
            width: 100%;
            max-width: 768px;
            height: 100%;
            padding: 32px;
            display: flex;
            flex-direction: column;
            align-items: center;
            background: rgba(0, 0, 0, .0625);
            border-radius: 12px;
            box-shadow: 0 8px 24px -16px rgba(0, 0, 0, .4);
            box-sizing: border-box;
        }

        body .content .drives-list {
            width: 100%;
            margin-bottom: 32px;
        }

        body .content .drives-list h2 {
            margin: 0 0 12px 0;
            padding: 0;
        }

        body .content .drives-list .placeholder {
            width: 100%;
            height: 128px;
            display: flex;
            justify-content: center;
            align-items: center;
            background: rgba(255, 255, 255, .75);
            border-radius: 12px;
        }

        body .content a {
            font-size: 1.1em;
            font-weight: bold;
            margin-left: auto;
            padding: 8px 16px;
            text-decoration: none;
            color: white;
            background: #2C5B9C;
            box-shadow: 0 8px 24px -16px rgba(0, 0, 0, .4);
            box-sizing: border-box;
            transition: all 250ms ease;
            cursor: pointer;
        }

        body .content a:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <h1>carSharer</h1>

    <div class="content">
        <div class="drives-list reserved-drives">
            <h2>Meine reservierten Fahrten</h2>

            <div class="placeholder">
                <p>Keine Fahrten reserviert.</p>
            </div>
        </div>

        <div class="drives-list open-drives">
            <h2>Offene Fahrten</h2>

            <div class="placeholder">
                <p>Keine Fahrten offen.</p>
            </div>
        </div>

        <a href="new_drive">Fahrt erstellen</a>
    </div>
</body>
</html>