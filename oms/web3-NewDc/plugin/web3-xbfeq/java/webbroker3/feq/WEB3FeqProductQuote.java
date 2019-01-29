head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProductQuote.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������(WEB3FeqProductQuote)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  䈋�(���u) �V�K�쐬
                   2005/07/27 鰊](���u) ���r���[
*/
package webbroker3.feq;

import java.sql.Timestamp;
import java.util.Date;


/**
 * (�O�������������) <BR>
 * �O�������������
 * 
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3FeqProductQuote 
{
    
    /**
     * (����) <BR>
     * �����B
     */
    private double currentPrice = 0;
    
    /**
     * (�O����) <BR>
     * �O����B
     */
    private double comparedPreviousDay = 0;
    
    /**
     * (�������\����) <BR>
     * �������\���ԁB
     */
    private Date currentPricePublicTime = null;
    
    /**
     * (�����擾�敪) <BR>
     * �����擾�敪�B <BR>
     *  <BR>
     * 1�F���� <BR>
     * 2�F�O���I�l <BR>
     * 3�F�����I�l <BR>
     */
    private String currentPriceGetDiv;
    
    /**
     * (�����敪) <BR>
     * �����敪�B <BR>
     *  <BR>
     * 1�F���ݒl <BR>
     * 2�F���C�z�l <BR>
     * 3�F���C�z�l <BR>
     * 4�F�O���I�l <BR>
     */
    private String currentPriceDiv;
    
    /**
     * (�s��R�[�h) <BR>
     * �s��R�[�h�B
     */
    private String marketCode;
    
    /**
     * @@roseuid 42CE39E9007D
     */
    public WEB3FeqProductQuote() 
    {
     
    }
    
    /**
     * (set����) <BR>
     * �������Z�b�g����B
     * @@param l_dblCurrentPrice - (����) <BR>
     * �����B
     * @@roseuid 4282C7840235
     */
    public void setCurrentPrice(double l_dblCurrentPrice) 
    {
        this.currentPrice = l_dblCurrentPrice;
    }
    
    /**
     * (get����) <BR>
     * �������擾����B
     * @@return double
     * @@roseuid 4282C7840254
     */
    public double getCurrentPrice() 
    {
        return this.currentPrice;
    }
    
    /**
     * (set�O����) <BR>
     * �O������Z�b�g����B
     * @@param l_dblComparedPreviousDay - (�O����) <BR>
     * �O����B
     * @@roseuid 4282C7840255
     */
    public void setComparedPreviousDay(double l_dblComparedPreviousDay) 
    {
        this.comparedPreviousDay = l_dblComparedPreviousDay;
    }
    
    /**
     * (get�O����)�@@<BR>
     * �O������擾����B
     * @@return double
     * @@roseuid 4282C7840264
     */
    public double getComparedPreviousDay() 
    {
        return this.comparedPreviousDay;
    }
    
    /**
     * (set�������\����) <BR>
     * �������\���Ԃ��Z�b�g����B
     * @@param l_tsCurrentPricePublicTime - (�������\����) <BR>
     * �������\���ԁB
     * @@roseuid 4282C7840274
     */
    public void setCurrentPricePublicTime(Timestamp l_tsCurrentPricePublicTime) 
    {
        this.currentPricePublicTime = l_tsCurrentPricePublicTime;
    }
    
    /**
     * (get�������\����) <BR>
     * �������\���Ԃ��擾����B
     * @@return Date
     * @@roseuid 4282C7840283
     */
    public Date getCurrentPricePublicTime() 
    {
        return this.currentPricePublicTime;
    }
    
    /**
     * (set�����擾�敪) <BR>
     * �����擾�敪���Z�b�g����B
     * @@param l_strCurrentPriceGetDiv - (�����擾�敪) <BR>
     * �����擾�敪�B
     * @@roseuid 4282C7840293
     */
    public void setCurrentPriceGetDiv(String l_strCurrentPriceGetDiv) 
    {
        this.currentPriceGetDiv = l_strCurrentPriceGetDiv;
    }
    
    /**
     * (get�����擾�敪) <BR>
     * �����擾�敪���擾����B
     * @@return String
     * @@roseuid 4282C7840295
     */
    public String getCurrentPriceGetDiv() 
    {
        return this.currentPriceGetDiv;
    }
    
    /**
     * (set�����敪) <BR>
     * �����敪���Z�b�g����B
     * @@param l_strCurrentPriceDiv - (�����敪) <BR>
     * �����敪�B
     * @@roseuid 4292B35C0241
     */
    public void setCurrentPriceDiv(String l_strCurrentPriceDiv) 
    {
        this.currentPriceDiv = l_strCurrentPriceDiv;
     
    }
    
    /**
     * (get�����敪) <BR>
     * �����敪���擾����B
     * @@return String
     * @@roseuid 4292B35C0243
     */
    public String getCurrentPriceDiv() 
    {
        return this.currentPriceDiv;
    }
    
    /**
     * (set�s��R�[�h) <BR>
     * �s��R�[�h���Z�b�g����B
     * @@param l_strMarketCode - (�s��R�[�h) <BR>
     * �s��R�[�h�B
     * @@roseuid 4282C78402A3
     */
    public void setMarketCode(String l_strMarketCode) 
    {
        this.marketCode = l_strMarketCode;
    }
    
    /**
     * (get�s��R�[�h) <BR>
     * �s��R�[�h���擾����B
     * @@return String
     * @@roseuid 4282C78402B2
     */
    public String getMarketCode() 
    {
        return this.marketCode;
    }
}
@
