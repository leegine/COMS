head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.16.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsBalRefTotalParIndexUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�I�v�V�����w���ʎc�����v(WEB3FuturesOptionsBalRefTotalParIndexUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/24 ����(���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����w���敨�I�v�V�����w���ʎc�����v)<BR>
 * �����w���敨�I�v�V�����w���ʎc�����v�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */
public class WEB3FuturesOptionsBalRefTotalParIndexUnit extends Message
{
    /**
     * (�w�����)<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
     */
    public String targetProductCode;
    
    /**
     * (�v�b�g�����ʎ������z)<BR>
     * �w���ʃv�b�g�����ʎ������z<BR>
     */
    public String putBuyCurrentPrice = "0";
    
    /**
     * (�R�[�������ʎ������z)<BR>
     * �w���ʃR�[�������ʎ������z<BR>
     */
    public String callBuyCurrentPrice = "0";
    
    /**
     * (�����ʎ������z)<BR>
     * �w���ʔ����ʎ������z<BR>
     */
    public String buyCurrentPrice = "0";
    
    /**
     * (�v�b�g�����ʎ������z)<BR>
     * �w���ʃv�b�g�����ʎ������z<BR>
     */
    public String putSellCurrentPrice = "0";
    
    /**
     * (�R�[�������ʎ������z)<BR>
     * �w���ʃR�[�������ʎ������z<BR>
     */
    public String callSellCurrentPrice = "0";
    
    /**
     * (�����ʎ������z)<BR>
     * �w���ʔ����ʎ������z<BR>
     */
    public String sellCurrentPrice = "0";
    
    /**
     * (�v�b�g�����ʑ�����)<BR>
     * �w���ʃv�b�g�����ʑ�����<BR>
     */
    public String putBuyTotalQuantity = "0";
    
    /**
     * (�R�[�������ʑ�����)<BR>
     * �w���ʃR�[�������ʑ�����<BR>
     */
    public String callBuyTotalQuantity = "0";
    
    /**
     * (�����ʑ�����)<BR>
     * �w���ʔ����ʑ����� <BR>
     */
    public String buyTotalQuantity = "0";
    
    /**
     * (�v�b�g�����ʑ�����)<BR>
     * �w���ʃv�b�g�����ʑ�����<BR>
     */
    public String putSellTotalQuantity = "0";
    
    /**
     * (�R�[�������ʑ�����)<BR>
     * �w���ʃR�[�������ʑ�����<BR>
     */
    public String callSellTotalQuantity = "0";
    
    /**
     * (�����ʑ�����)<BR>
     * �w���ʔ����ʑ�����<BR>
     */
    public String sellTotalQuantity = "0";
    
    /**
     * (���ʑ�����)<BR>
     * �w���ʌ��ʑ�����<BR>
     */
    public String totalQuantity = "0";
    
    /**
     * (�v�b�g�����ʕ]�����v���v)<BR>
     * �w���ʃv�b�g�����ʕ]�����v���v<BR>
     */
    public String putBuyAssetProfitLoss = "0";
    
    /**
     * (�R�[�������ʕ]�����v���v)<BR>
     * �w���ʃR�[�������ʕ]�����v���v<BR>
     */
    public String callBuyAssetProfitLoss = "0";
    
    /**
     * (�����ʕ]�����v���v)<BR>
     * �w���ʔ����ʕ]�����v���v<BR>
     */
    public String buyAssetProfitLoss = "0";
    
    /**
     * (�v�b�g�����ʕ]�����v���v)<BR>
     * �w���ʃv�b�g�����ʕ]�����v���v<BR>
     */
    public String putSellAssetProfitLoss = "0";
    
    /**
     * (�R�[�������ʕ]�����v���v)<BR>
     * �w���ʃR�[�������ʕ]�����v���v<BR>
     */
    public String callSellAssetProfitLoss = "0";
    
    /**
     * (�����ʕ]�����v���v)<BR>
     * �w���ʔ����ʕ]�����v���v<BR>
     */
    public String sellAssetProfitLoss = "0";
    
    /**
     * (�]�����v���v)<BR>
     * �w���ʕ]�����v���v<BR>
     */
    public String appraisalProfitLoss = "0";
    
    /**
     * (�v�b�g�����ʕ]�����v���v(���o�))<BR>
     * �w���ʃv�b�g�����ʕ]�����v���v(���o�)<BR>
     */
    public String putBuyAssetProfitLossCost = "0";
    
    /**
     * (�R�[�������ʕ]�����v���v(���o�))<BR>
     * �w���ʃR�[�������ʕ]�����v���v(���o�)<BR>
     */
    public String callBuyAssetProfitLossCost = "0";
    
    /**
     * (�����ʕ]�����v���v(���o�))<BR>
     * �w���ʔ����ʕ]�����v���v(���o�)<BR>
     */
    public String buyAssetProfitLossCost = "0";
    
    /**
     * (�v�b�g�����ʕ]�����v���v(���o�))<BR>
     * �w���ʃv�b�g�����ʕ]�����v���v(���o�)<BR>
     */
    public String putSellAssetProfitLossCost = "0";
    
    /**
     * (�R�[�������ʕ]�����v���v(���o�))<BR>
     * �w���ʃR�[�������ʕ]�����v���v(���o�)<BR>
     */
    public String callSellAssetProfitLossCost = "0";
    
    /**
     * (�����ʕ]�����v���v(���o�))<BR>
     * �w���ʔ����ʕ]�����v���v(���o�)<BR>
     */
    public String sellAssetProfitLossCost = "0";
    
    /**
     * (�]�����v���v(���o�))<BR>
     * �w���ʕ]�����v���v(���o�)<BR>
     */
    public String appraisalProfitLossCost = "0";
}
@
