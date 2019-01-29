head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.14.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍψꗗ�s�N���X(WEB3OptionsCloseMarginGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ������ (���u) �V�K�쐬
              001: 2004/07/28 ���Ō� (���u) �Ή� �ڍא݌v�`�F�b�N�w�E���� (���{��) 
                   com.fitechlabs.xtrade.kernel.message.Message���p���B
Revesion History : 2007/06/08 ���^�] (���u) �d�l�ύX���f��No.640
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;
/**
 * (�����w���I�v�V�����ԍψꗗ�s)<BR>
 * �����w���I�v�V�����ԍψꗗ�s�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginGroup extends Message
{
    
    /**
     * (�����R�[�h)
     */
    public String opProductCode;
    
    /**
     * (������)
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
     * (���ʐ�)
     */
    public String contractQuantity;
    
    /**
     * (���P��)
     */
    public String contractPrice;
    
    /**
     * (���Ϗ�ԋ敪)<BR>
     * ���L�̂����ꂩ�B<BR>
     * 1�F������<BR>
     * 2�F���ϒ�<BR>
     */
    public String settlementState;
    
    /**
     * (�������z)
     */
    public String contractExecPrice;
    
    /**
     * (���萔��)<BR>
     * ���萔��(����ō�)<BR>
     */
    public String contractCommission;
    
    /**
     * (���ݒl)
     */
    public String currentPrice;
    
    /**
     * (���v)
     */
    public String income;
    
    /**
     * (���v(���o�))
     */
    public String incomeCost;
    
    /**
     * (���ʖ���)
     */
    public WEB3FuturesOptionsContractUnit[] contractUnits; 
    
    /**
     * (����敪)<BR>
     * 1�F�[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@NULL�F���̑�<BR>
     */
    public String sessionType;
}
@
