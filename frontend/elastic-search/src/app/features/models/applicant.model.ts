export class Applicant {
    name: string = '';
    surname: string = '';
    degree: string = '';
    latitude: number = 0;
    longitude: number = 0;

    constructor(object: any) {
        if (!object) {
            return;
        }

        this.name = object.name;
        this.surname = object.surname;
        this.degree = object.degree;
        this.latitude = object.latitude;
        this.longitude = object.longitude;
    }
}
