head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityRegulationTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : ��������������K���@@�R�[�h��`�C���^�t�F�C�X (WEB3EquityRegulationTypeDef.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2004/12/22 SRA��� �쐬
*/
package webbroker3.equity.define;

/**
 * ��������������K���@@�R�[�h��`�C���^�t�F�C�X
 *                                                                     
 * @@author SRA���
 * @@version 1.0
 */
public interface WEB3EquityRegulationTypeDef {
    /**
     * 1:������~��
     */
    public final static String REG_TYPE_TRDSTOP = "1";
    
    /**
     * 2:���������~��
     */
    public final static String REG_TYPE_STOCKTRDSTOP = "2";
    
    /**
     * 3:�M�p�����~��
     */
    public final static String REG_TYPE_MARTRDSTOP = "3";
    
    /**
     * 4�F���x�M�p�����~��
     */
    public final static String REG_TYPE_MARSYSTRDSTOP = "4";
    
    /**
     * 5�F��ʐM�p�����~�� 
     */
    public final static String REG_TYPE_MARGENTRDSTOP = "5";
    
    /**
     * 6�F�������t��~��
     */
    public final static String REG_TYPE_BUYCASHSTOP = "6";
    
    /**
     * 7�F�������t��~��
     */
    public final static String REG_TYPE_SELLCASHSTOP = "7";
    
    /**
     * 8�F�~�j�������~��
     */
    public final static String REG_TYPE_MINISTOCKSTOP = "8";
    
    /**
     * 9�F�~�j�����t��~�� 
     */
    public final static String REG_TYPE_BUYMINISTOCKSTOP = "9";
    
    /**
     * 10�F�~�j�����t��~��
     */
    public final static String REG_TYPE_SELLMINISTOCKSTOP = "10";
    
    /**
     * 11�F�M�p�V�K����~��
     */
    public final static String REG_TYPE_MARGINSTOP = "11";
    
    /**
     * 12�F�M�p�V�K����~��
     */
    public final static String REG_TYPE_LONGMARGINSTOP = "12";
    
    /**
     * 13�F�M�p�V�K����~��
     */
    public final static String REG_TYPE_SHORTMARGINSTOP = "13";
    
    /**
     * 14�F���x�M�p�V�K����~��
     */
    public final static String REG_TYPE_MARGINSYSSTOP = "14";
    
    /**
     * 15�F��ʐM�p�V�K����~��
     */
    public final static String REG_TYPE_MARGINGENSTOP = "15";
    
    /**
     * 16�F���x�M�p�V�K����~��
     */
    public final static String REG_TYPE_LONGMARGINSYSSTOP = "16";
    
    /**
     * 17�F���x�M�p�V�K����~��
     */
    public final static String REG_TYPE_SHORTMARGINSYSSTOP = "17";
    
    /**
     * 18�F��ʐM�p�V�K����~��
     */
    public final static String REG_TYPE_LONGMARGINGENSTOP = "18";
    
    /**
     * 19�F��ʐM�p�V�K����~��
     */
    public final static String REG_TYPE_SHORTMARGINGENSTOP = "19";
    
    /**
     * 20�F�M�p�ԍϒ�~��
     */
    public final static String REG_TYPE_CLOSEMARGINSTOP = "20";
    /**
     * 21�F�M�p�ԍϔ���~��
     */
    public final static String REG_TYPE_LONGCLOSEMARGINSTOP = "21";
    
    /**
     * 22�F�M�p�ԍϔ���~��
     */
    public final static String REG_TYPE_SHORTCLOSEMARGINSTOP = "22";
    
    /**
     * 23�F���x�M�p�ԍϒ�~��
     */
    public final static String REG_TYPE_CLOSEMARGINSYSSTOP = "23";
    
    /**
     * 24�F��ʐM�p�ԍϒ�~��
     */
    public final static String REG_TYPE_CLOSEMARGINGENSTOP = "24";
    
    /**
     * 25�F���x�M�p�ԍϔ���~��
     */
    public final static String REG_TYPE_LONGCLOSEMARGINSYSSTOP = "25";
    
    /**
     * 26�F���x�M�p�ԍϔ���~��
     */
    public final static String REG_TYPE_SHORTCLOSEMARGINSYSSTOP = "26";
    
    /**
     * 27�F��ʐM�p�ԍϔ���~��
     */
    public final static String REG_TYPE_LONGCLOSEMARGINGENSTOP = "27";
    
    /**
     * 28�F��ʐM�p�ԍϔ���~��
     */
    public final static String REG_TYPE_SHORTCLOSEMARGINGENSTOP = "28";
    
    /**
     * 29�F�M�p�������n��~��
     */
    public final static String REG_TYPE_SWAPMARGINSTOP = "29";
    
    /**
     * 30�F�M�p������~��
     */
    public final static String REG_TYPE_LONGSWAPMARGINSTOP = "30";
        
    /**
     * 31�F�M�p���n��~��
     */
    public final static String REG_TYPE_SHORTSWAPMARGINSTOP = "31";
    
    /**
     * 32�F���x�M�p�������n��~��
     */
    public final static String REG_TYPE_SWAPMARGINSYSSTOP = "32";
    
    /**
     * 33�F��ʐM�p�������n��~��
     */
    public final static String REG_TYPE_SWAPMARGINGENSTOP = "33";
    
    /**
     * 34�F���x�M�p������~��
     */
    public final static String REG_TYPE_LONGSWAPMARGINSYSSTOP = "34";
    
    /**
     * 35�F���x�M�p���n��~��
     */
    public final static String REG_TYPE_SHORTSWAPMARGINSYSSTOP = "35";
    
    /**
     * 36�F��ʐM�p������~��
     */
    public final static String REG_TYPE_LONGSWAPMARGINGENSTOP = "36";
    
    /**
     * 37�F��ʐM�p���n��~�� 
     */
    public final static String REG_TYPE_SHORTSWAPMARGINGENSTOP = "37";
    
    /**
     * 38�F���s���������~��
     */
    public final static String REG_TYPE_SPOTMARKETORDDESSTOP = "38";
    
    /**
     * 39�F���s�������t��~�� 
     */
    public final static String REG_TYPE_BUYSPOTMARKETORDDESSTOP = "39";
    
    /**
     * 40�F���s�������t��~��
     */
    public final static String REG_TYPE_SELLSPOTMARKETORDDESSTOP = "40";
    
    /**
     * 41�F���s�M�p�V�K����~��
     */
    public final static String REG_TYPE_MMARKETORDDESSTOP = "41";
    
    /**
     * 42�F���s�M�p�V�K����~��
     */
    public final static String REG_TYPE_LONGMMARKETORDDESSTOP = "42";
    
    /**
     * 43�F���s�M�p�V�K����~��
     */
    public final static String REG_TYPE_SHORTMMARKETORDDESSTOP = "43";
    
    /**
     * 44�F���s���x�M�p�V�K����~��
     */
    public final static String REG_TYPE_MSMARKETORDDESSTOP = "44";
    
    /**
     * 45�F���s��ʐM�p�V�K����~��
     */
    public final static String REG_TYPE_MGMARKETORDDESSTOP = "45";
    
    /**
     * 46�F���s���x�M�p�V�K����~��
     */
    public final static String REG_TYPE_LONGMSMARKETORDDESSTOP = "46";
    
    /**
     * 47�F���s���x�M�p�V�K����~��
     */
    public final static String REG_TYPE_SHORTMSMARKETORDDESSTOP = "47";
    
    /**
     * 48�F���s��ʐM�p�V�K����~��
     */
    public final static String REG_TYPE_LONGMGMARKETORDDESSTOP = "48";
    
    /**
     * 49�F���s��ʐM�p�V�K����~�� 
     */
    public final static String REG_TYPE_SHORTMGMARKETORDDESSTOP = "49";
    
    /**
     * 50�F���s�M�p�ԍϒ�~��
     */
    public final static String REG_TYPE_CMMARKETORDDESSTOP = "50";
        
    /**
     * 51:���s�M�p�ԍϔ���~��
     */
    public final static String REG_TYPE_LONGCMMARKETORDDESSTOP = "51";
    
    /**
     * 52�F���s�M�p�ԍϔ���~��
     */
    public final static String REG_TYPE_SHORTCMMARKETORDDESSTOP = "52";
    
    /**
     * 53�F���s���x�M�p�ԍϒ�~��
     */
    public final static String REG_TYPE_CMSMARKETORDDESSTOP = "53";
    
    /**
     * 54�F���s��ʐM�p�ԍϒ�~��
     */
    public final static String REG_TYPE_CMGMARKETORDDESSTOP = "54";
    
    /**
     * 55�F���s���x�M�p�ԍϔ���~��
     */
    public final static String REG_TYPE_LONGCMSMARKETORDDESSTOP = "55";
    
    /**
     * 56�F���s���x�M�p�ԍϔ���~��
     */
    public final static String REG_TYPE_SHORTCMSMARKETORDDESSTOP = "56";
    
    /**
     * 57�F���s��ʐM�p�ԍϔ���~��
     */
    public final static String REG_TYPE_LONGCMGMARKETORDDESSTOP = "57";
    
    /**
     * 58�F���s��ʐM�p�ԍϔ���~��
     */
    public final static String REG_TYPE_SHORTCMGMARKETORDDESSTOP = "58";

        
}
@
