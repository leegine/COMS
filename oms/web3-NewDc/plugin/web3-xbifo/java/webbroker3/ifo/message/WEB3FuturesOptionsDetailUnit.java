head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsDetailUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�I�v�V�����c���Ɖ�׃N���X(WEB3FuturesOptionsDetailUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 ������ �V�K�쐬         
Revesion History : 2007/06/08 ���^�] (���u) �d�l�ύX���f��No.640
*/
package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�����w���敨�I�v�V�����c���Ɖ��)<BR>
 * �����w���敨�I�v�V�����c���Ɖ�׃N���X
 * @@author ������
 * @@version 1.0
 */
public class WEB3FuturesOptionsDetailUnit extends Message
{
   
    /**
     * (�h�c)<BR>
     * ���ʂh�c<BR>
     */
    public String id;
    
    /**
     * (�����R�[�h)<BR>
     * �敨OP�����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * �敨OP��������<BR>
     */
    public String productName;
    
    /**
     * (�w�����)<BR>
     * 0005�FTOPIX�@@0018�F���o225�@@0016�F���o300�@@0019�F�~�j���o225<BR>
     */
    public String targetProductCode;
    
    /**
     * (����)<BR>
     * YYYYMM�`��<BR>
     */
    public String delivaryMonth;
    
    /**
     * (�I�v�V�������i�敪)<BR>
     * F�F�敨  P�F�v�b�g�I�v�V����  C�F�R�[���I�v�V����<BR>
     */
    public String opProductType;
 
    /**
     * (�s�g���i)<BR>
     */    
    public String strikePrice;
    
    /**
     * (����s��)<BR>
     * 1�F�����@@2�F���
     */
    public String marketCode;
    
    /**
     * (���敪)<BR>
     * 1�F�����@@2�F����
     */
    public String contractType;
    
    /**
     * (����)<BR>
     */
    public Date openDate;
    
    /**
     * (������)<BR>
     */
    public String contractOrderQuantity;
    
    /**
     * (���P��)<BR>
     */
    public String contractPrice;
    
    /**
     * (���Ϗ�ԋ敪)<BR>
     * 1�F�����ρ@@2�F���ϒ�<BR>
     */
    public String settlementState;
    
    /**
     * (�������z)<BR>
     */
    public String contractExecPrice;
    
    /**
     * (���萔��)<BR>
     * ���萔��(����ō�)<BR>
     */
    public String contractCommission;
    
    /**
     * (����ŏI��)<BR>
     */
    public Date lastTradingDate;
    
    /**
     * (����)<BR>
     */
    public String currentPrice = null;
    
    /**
     * (�O����)<BR>
     */
    public String comparedPreviousDay = null;
    
    /**
     * (�����擾����)<BR>
     */
    public String currentPriceTime = null;
    
    /**
     * (���v)<BR>
     */
    public String income = null;
    
    /**
     * (���v(���o�))<BR>
     */
    public String incomeCost = null;

    /**
     * (����敪)<BR>
     * 1�F�[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@NULL�F���̑�<BR>
     */
    public String sessionType;
}
@
