head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.44.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoProductQuote.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�������(WEB3IfoCurrentInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 ������ �V�K�쐬         
*/
package webbroker3.ifo;

import java.sql.Timestamp;


/**
 * (�敨OP�������)<BR>
 * �敨OP������������ێ�����f�[�^�N���X�B <BR>
 * �������擾���\�b�h�̖߂�l��ێ�����B<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3IfoProductQuote 
{
   
    /**
     * �����B
     */
    private double currentPrice = 0;
    
    /**
     * �O����B
     */
    private double comparedPreviousDay = 0;
    
    /**
     * �������\���ԁB
     */
    private Timestamp currentPriceTime;
    
    /**
     * �����擾�敪�B<BR>
     * �i1�F�����@@2�F�O���I�l�j<BR>
     */
    private String currentPriceDiv;
    
    /**
     * �s��R�[�h�B
     */
    private String marketCode;
    
    /**
     * �R���X�g���N�^
     * @@roseuid 41AC18FC02AF
     */
    public WEB3IfoProductQuote() 
    {
     
    }
    
    /**
     * (set����)<BR>
     * �������Z�b�g����B<BR>
     * @@param l_dblCurrentPrice - �����B
     * @@roseuid 41AC14EE038A
     */
    public void setCurrentPrice(double l_dblCurrentPrice) 
    {
         this.currentPrice = l_dblCurrentPrice;
    }
    
    /**
     * (get����)<BR>
     * �������擾����B<BR>
     * @@return double
     * @@roseuid 41AC14EE038C
     */
    public double getCurrentPrice() 
    {
         return currentPrice;
    }
    
    /**
     * (set�O����)<BR>
     * �O������Z�b�g����B<BR>
     * @@param l_dblComparedPreviousDay - �O����B
     * @@roseuid 41B549FC00C8
     */
    public void setComparedPreviousDay(double l_dblComparedPreviousDay) 
    {
         this.comparedPreviousDay = l_dblComparedPreviousDay;
    }
    
    /**
     * (get�O����)<BR>
     * �O������擾����B<BR>
     * @@return double
     * @@roseuid 41B549FC00CA
     */
    public double getComparedPreviousDay() 
    {
        return comparedPreviousDay;
    }
    
    /**
     * (set�������\����)<BR>
     * �������\���Ԃ��Z�b�g����B<BR>
     * @@param l_tsCurrentPriceTime - �������\���ԁB
     * @@roseuid 41AC14EE0399
     */
    public void setCurrentPriceTime(Timestamp l_tsCurrentPriceTime) 
    {
         this.currentPriceTime = l_tsCurrentPriceTime;
    }
    
    /**
     * (get�������\����)<BR>
     * �������\���Ԃ��擾����B<BR>
     * @@return Timestamp
     * @@roseuid 41AC14EE039B
     */
    public Timestamp getCurrentPriceTime() 
    {
        return currentPriceTime;
    }
    
    /**
     * (set�����擾�敪)<BR>
     * �����擾�敪���Z�b�g����B<BR>
     * @@param l_strCurrentPriceDiv - �����擾�敪�B
     * @@roseuid 41AC14EE039C
     */
    public void setCurrentPriceDiv(String l_strCurrentPriceDiv) 
    {
         this.currentPriceDiv = l_strCurrentPriceDiv;
    }
    
    /**
     * (get�����擾�敪)<BR>
     * �����擾�敪���擾����B<BR>
     * @@return String
     * @@roseuid 41AC14EE039E
     */
    public String getCurrentPriceDiv() 
    {
         return currentPriceDiv;
    }
    
    /**
     * (set�s��R�[�h)<BR>
     * �s��R�[�h���Z�b�g����B<BR>
     * @@param l_strMarketCode - �s��R�[�h�B
     * @@roseuid 41AC14EE039F
     */
    public void setMarketCode(String l_strMarketCode) 
    {
         this.marketCode = l_strMarketCode;
    }
    
    /**
     * (get�s��R�[�h)<BR>
     * �s��R�[�h���擾����B<BR>
     * @@return String
     * @@roseuid 41AC14EE03A1
     */
    public String getMarketCode() 
    {
        return marketCode;
    }
}
@
