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

        body .content .drives-list .drives-list-inner {
            width: 100%;
            display: grid;
            grid-template-columns: repeat(2, minmax(0, 1fr));
            grid-gap: 16px;
            grid-auto-flow: column;
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

        body .content .drives-list .drives-list-inner .drive-card {
            width: 100%;
            height: 256px;
            padding: 32px;
            display: flex;
            flex-direction: column;
            align-items: center;
            background: rgba(255, 255, 255, .75);
            border-radius: 12px;
            box-sizing: border-box;
        }

        body .content .drives-list .drives-list-inner .drive-card p {
            margin: 0;
        }

        body .content .drives-list .drives-list-inner .drive-card button {
            width: 4em;
            height: 4em;
            margin-bottom: auto;
        }

        body .content .drives-list .drives-list-inner .drive-card .card-row {
            margin-bottom: 8px;
            display: flex;
            flex-direction: row;
            align-items: center;
        }

        body .content .drives-list .drives-list-inner .drive-card .card-row b {
            margin-right: 8px;
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

            <#if (drives?size > 0)>
                <div class="drives-list-inner">
                    <#list drives as drive>
                        <div class="drive-card">
                            <button type="submit">X</button>
                            <div class="card-row">
                                <b>Von:</b>
                                <p>${drive.startOrt}</p>
                            </div>
                            <div class="card-row">
                                <b>Bis:</b>
                                <p>${drive.zielOrt}</p>
                            </div>
                            <div class="card-row">
                                <b>Status:</b>
                                <p>Offen</p>
                            </div>
                        </div>
                    </#list>
                </div>
            </#if>
            <#if (drives?size = 0)>
                <div class="placeholder">
                    <p>Keine Fahrten reserviert.</p>
                </div>
            </#if>
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