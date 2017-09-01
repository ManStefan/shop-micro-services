import { OpaqueToken } from '@angular/core';

// import translations
import { LANG_EN_NAME, LANG_EN_TRANS } from './lang-en';
import { LANG_RO_NAME, LANG_RO_TRANS } from './lang-ro';


// translation token
export const TRANSLATIONS = new OpaqueToken('translations');


// all translations
const dictionary = {
  [LANG_EN_NAME]: LANG_EN_TRANS,
  [LANG_RO_NAME]: LANG_RO_TRANS
};

// providers
export const TRANSLATION_PROVIDERS = [
  { provide: TRANSLATIONS, useValue: dictionary },
];
