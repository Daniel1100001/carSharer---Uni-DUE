<html>
<head>
    <title>Fahrt Details</title>

    <style>
        body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 64px 32px;
            font-family: Arial;
            display: flex;
            flex-direction: column;
            align-items: center;
            box-sizing: border-box;
        }

        body .drive-details {
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

        body .drive-details .details-head {
            width: 100%;
            margin-bottom: 32px;
            padding-bottom: 32px;
            display: flex;
            flex-direction: column;
            align-items: center;
            border-bottom: 1px solid rgba(0, 0, 0, .4);
            box-sizing: border-box;
        }

        body .drive-details .details-head button {
            width: 3em;
            height: 3em;
            font-size: 2em;
            margin: 0 auto 32px auto;
        }

        body .drive-details .details-head table {
            width: 100%;
        }
        body .drive-details .details-head table tr {
            width: 100%;
            margin: 4px 0;
            display: grid;
            grid-gap: 16px;
            grid-template-columns: repeat(2, 1fr);
        }


        body .drive-details .details-head table tr td:first-child {
            font-weight: bold;
        }

        body .drive-details .details-head table tr td textarea {
            width: 100%;
            min-height: 128px;
            max-height: 256px;
            resize: vertical;
        }

        body .drive-details .details-actions {
            width: 100%;
            margin-bottom: 32px;
            padding-bottom: 32px;
            border-bottom: 1px solid rgba(0, 0, 0, .4);
            box-sizing: border-box;
        }

        body .drive-details .details-actions h2 {
            margin: 0 0 16px 0;
            padding: 0;
        }

        body .drive-details .details-actions table {
            width: 100%;
        }

        body .drive-details .details-actions table tr {
            width: 100%;
            margin: 8px 0;
            display: grid;
            grid-gap: 16px;
            grid-template-columns: 2fr 1fr;
        }

        body .drive-details .details-actions table tr td input {
            width: 64px;
            margin-left: 8px;
        }
        body .drive-details .details-actions table tr td button {
            width: 100%;
            height: 100%;
            padding: 5px 15px;
            color: white;
            font-size: 1.1em;
            font-weight: bold;
            border: none;
            cursor: pointer;
            box-sizing: border-box;
            transition: all 250ms ease;
        }

        body .drive-details .details-actions table tr td button:hover {
            opacity: 0.8;
        }

        body .drive-details .details-actions table tr td button.reserve {
            background: #2C5B9C;
        }

        body .drive-details .details-actions table tr td button.delete {
            background: #E74C3C;
        }

        body .drive-details .details-ratings {
            width: 100%;
            display: flex;
            flex-direction: column;
        }

        body .drive-details .details-ratings h2 {
            margin: 0 0 16px 0;
        }

        body .drive-details .details-ratings h3 {
            font-weight: normal;
        }

        body .drive-details .details-ratings table {
            width: 100%;
            margin-bottom: 64px;
        }

        body .drive-details .details-ratings table tr {
            width: 100%;
            margin: 8px 0;
            display: grid;
            grid-gap: 16px;
            grid-template-columns: 4fr 4fr 1fr;
        }

        body .drive-details .details-ratings table tr td:first-child {
            font-weight: bold;
        }

        body .drive-details .details-ratings table tr td:last-child {
            text-align: center;
        }

        body .drive-details .details-ratings a {
            text-decoration: none;
            margin-left: auto;
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

        body .drive-details .details-ratings a:hover {
            opacity: 0.8;
        }
    </style>
</head>
<body>
    <h1>Informationen</h1>
	
    <div class="drive-details">
        <div class="details-head">
            <button>X</button>

            <table>
                <tr>
                    <td>Anbieter:</td>
                    <td><type="text" name="emailAnbieter"></td>
                </tr>
                <tr>
                    <td>Fahrtdatum und -uhrzeit:</td>
                    <td>${driveToView.fahrtDatumString}  ${driveToView.fahrtZeitString}</td>
                </tr>
                <tr>
                    <td>Von:</td>
                    <td>${driveToView.startOrt}</td>
                </tr>
                <tr>
                    <td>Nach:</td>
                    <td>${driveToView.zielOrt}</td>
                </tr>
                <tr>
                    <td>Anzahl freier Plätze:</td>
                    <td><#assign x="freiePlaetze"></td>
                </tr>
                <tr>
                    <td>Fahrtkosten:</td>
                    <td>${driveToView.fahrtkosten} €</td>
                </tr>
                <tr>
                    <td>Status:</td>
                    <td>${driveToView.status}</td>
                </tr>
                <tr>
                    <td>Beschreibung:</td>
                    <td>${driveToView.beschreibung}</td>
                </tr>
            </table>
        </div>

        <div class="details-actions">
            <h2>Aktionsleiste</h2>

            <table>
                <tr>
                    <td>
                        Anzahl Plätze für Reservierung:
                        <input type="number" min="1" max="10" value="1">
                    </td>
                    <td><button class="reserve" type="submit">Fahrt reservieren</button></td>
                </tr>
                <tr>
                    <td></td>
                    <td><button class="delete" type="submit">Fahrt löschen</button></td>
                </tr>
            </table>
        </div>

        <div class="details-ratings">
        	<div class="bewertung-list bewertungen">
            <h2>Bewertungen</h2>
				<#if  bewertungen?size gt 0>
                <div class="bewertung-list-inner">
                <#list bewertungen as bewertung>
            <h3><b>Durchschnittsrating:</b> 4,33</h3>
            <table>
                <tr>
                    <td>${bewertung.email}</td>
                    <td>${bewertung.textnachricht}</td>
                    <td>${bewertung.rating}</td>
                </tr>
            </table>
			</#list>
            <a class="rate" href="new_rating">Fahrt bewerten</a>
        </div>
    </div>
</body>
</html>