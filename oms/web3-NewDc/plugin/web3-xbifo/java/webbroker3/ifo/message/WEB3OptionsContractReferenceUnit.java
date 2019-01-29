head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsContractReferenceUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�������ʏƉ��(WEB3OptionsContractReferenceUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ���� (���u) �V�K�쐬
Revesion History : 2007/06/08 �Ј��� (���u) �d�l�ύX���f��No.682
*/

package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�����w���I�v�V�������ʏƉ��)<BR>
 * �����w���I�v�V�������ʏƉ�s�N���X<BR>
 * @@author ����
 * @@version 1.0
 */
public class WEB3OptionsContractReferenceUnit extends Message
{
    
    /**
     * (�h�c)<BR>
     * ���ʂh�c<BR>
     */
    public String id;
    
    /**
     * (�����R�[�h)<BR>
     */
    public String opProductCode;
    
    /**
     * (������)<BR>
     */
    public String opProductName;
    
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
     * P�F�v�b�g�I�v�V���� C�F�R�[���I�v�V����<BR>
     */
    public String opProductType;
    
    /**
     * (�s�g���i)
     */
    public String strikePrice;
    
    /**
     * (����s��)<BR>
     * 1�F�����@@2�F���<BR>
     */
    public String marketCode;
    
    /**
     * (���敪)<BR>
     * 1�F�����@@2�F����<BR>
     */
    public String contractType;
    
    /**
     * (����)
     */
    public Date openDate;
    
    /**
     * (������)
     */
    public String contractOrderQuantity;
    
    /**
     * (���P��)
     */
    public String contractPrice;
    
    /**
     * (���Ϗ�ԋ敪)<BR>
     * 0�F���ύρ@@1�F�����ρ@@2�F���ϒ�<BR>
     */
    public String settlementState;
    
    /**
     * (�������z)
     */
    public String contractExecPrice;
    
    /**
     * (���萔��)
     * ���萔��(����ō�)
     */
    public String contractCommission;
    
    /**
     * (����ŏI��)
     */
    public Date lastTradingDate;
    
    /**
     * (���v)
     */
    public String income;
    
    /**
     * (���v(���o�))
     */
    public String incomeCost;
    
    /**
     * (����敪)<BR>
     * 1�F�[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@NULL�F���̑�<BR>
     */
    public String sessionType;
    
    /**
     * @@roseuid 40C0754900AB
     */
    public WEB3OptionsContractReferenceUnit() 
    {
     
    }
}
@
