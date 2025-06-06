import { Injectable } from '@angular/core';
import Holiday from '../models/Holiday';
import { ApiService } from './api.service';

@Injectable({ providedIn: 'root' })
export class HolidayService {
  apiUrl: string = "";

  constructor(private api: ApiService) {
    this.apiUrl = `${this.api.getApiUrl()}/holidays`;
  }

  getAllHolidays(): Promise<Holiday[]> {
    return this.api.get<Holiday[]>(`${this.apiUrl}`);
  }

  getHolidaysById(userId: number): Promise<Holiday[]> {
    return this.api.get<Holiday[]>(`${this.apiUrl}?userId=${userId}`);
  }
  
  // Crear una nueva vacación
  addHoliday(holiday: Holiday): Promise<Holiday> {
      return this.api.post<{ data: Holiday }>(this.apiUrl, holiday).then(res => res.data);
  }

  updateHoliday(holiday: Holiday): Promise<Holiday> {
    return this.api.put<Holiday>(`${this.apiUrl}`, holiday);
  }
  
  deleteHoliday(id: number): Promise<void> {
    return this.api.delete<void>(`${this.apiUrl}/${id}`);
  }

  //?Vacaciones en excel
  //admins
  getAllHolidaysInExcel(): Promise<Blob> {
    return this.api.getBlob(`${this.apiUrl}/report/xlsx?getAll=true`);
  }
  getHolidaysByIdInExcel(userId: number): Promise<Blob> {
    console.log("userId", userId);
    return this.api.getBlob(`${this.apiUrl}/report/xlsx?getAll=false&userId=${userId}`);
  }

  //Usuarios
  getHolidaysByInExcelForUser(): Promise<Blob> {
    return this.api.getBlob(`${this.apiUrl}/report/xlsx`);
  }

}
