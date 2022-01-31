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
                    <td>${emailAnbieter}</td>
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
                    <td>${freiePlaetze}</td>
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
                        <form action="view_drive" method="GET" id="fahrtreservieren">
                    	<input type="hidden" name="driveFid" value="${driveToView.fid}">
                        <input type="number" name="zuResP" min="1" max="2" value="1">
                    </td>
                    <td><button class="reserve" type="submit" name="fahrtreservieren" form="fahrtreservieren" value="fahrtreservieren">Fahrt reservieren</button></td>
                    </form>
                </tr>
                <tr>
                    <td></td>
                    <form action="view_main" method="POST" id="fahrtlöschen">
                    <input type="hidden" name="driveFid" value="${driveToView.fid}">
                    <td><button class="delete" type="submit" name="fahrtlöschen" form="fahrtlöschen" value="fahrtlöschen">Fahrt löschen</button></td>
                    </form>
                </tr>
            </table>
        </div>
            <div class="bewertungen-list reservedDrives">
            <h2>Bewertungen</h2>
            <h3><b>Durchschnittsrating: ${durchschnittsRating}</b></h3>
            <table class="datatable">
                <tr>
        			<th>Email</th>  <th>Beschreibung</th> <th>Rating</th>
    			</tr>
                <#list bewertungen as bewertung>
				<tr>
				    <td>${bewertung.email}</td> <td>${bewertung.textnachricht}</td> <td>${bewertung.rating}</td>
				</tr>
				</#list>
                </tr> 
            </table>
         		<form action="new_rating" method="GET" id="fahrtbewerten">
                    <input type="hidden" name="driveFid" value="${driveToView.fid}">
                    <td><button type="submit" name="fahrtbewerten" form="fahrtbewerten" >Fahrt bewerten</button></td>
                </form>
            </div>
    </div>
</body>
</html>