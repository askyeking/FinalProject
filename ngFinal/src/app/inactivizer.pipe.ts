import { Pipe, PipeTransform } from '@angular/core';
import { MockNgModuleResolver } from '@angular/compiler/testing';
import { InventoryItem } from './models/inventory-item';

@Pipe({
  name: 'inactivizer'
})
export class InactivizerPipe implements PipeTransform {
  // this pipe is ued to remove all inactive items when loading inventory items
  transform(inputArray: InventoryItem[], args?: any): any {
    const outputArray = [];
    for (let i = 0; i < inputArray.length; i++) {
      if (inputArray[i].active) {
          outputArray.push(inputArray[i]);
      }
    }
    return outputArray;
  }

}
