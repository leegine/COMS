head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.11.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginContractGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����ԍό��ʈꗗ���׍s�N���X(WEB3OptionsCloseMarginContractGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 ������ �V�K�쐬
              001: 2004/07/28 ���Ō� (���u) �Ή� �ڍא݌v�`�F�b�N�w�E���� (���{��) 
                   com.fitechlabs.xtrade.kernel.message.Message���p���B
Revesion History : 2007/06/08   ���^�] (���u) �d�l�ύX���f��No.640
*/

package webbroker3.ifo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�����w���I�v�V�����ԍό��ʈꗗ���׍s)<BR>
 * �����w���I�v�V�����ԍό��ʈꗗ���׍s�N���X<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginContractGroup extends Message
{
      
    /**
     * (���P��)<BR>
     */
    public String contractPrice;
    
    /**
     * (����)<BR>
     */
    public Date openDate;
    
    /**
     * (�ԍϐ��ʁi�����������ʁj)<BR>
     */
    public String contractOrderQuantity;

    /**
     * (�����P���敪)<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     */
    public String limitPrice;

    /**
     * (��萔��)<BR>
     */
    public String execQuantity;
    
    /**
     * (���P��)<BR>
     */
    public String execPrice;
    
    /**
     * (���萔��)<BR>
     */
    public String contractCommission;
    
    /**
     * (���萔�������)<BR>
     */
    public String contractCommissionConsumptionTax;
    
    /**
     * (�������z)<BR>
     */
    public String contractExecTotalPrice;
    
    /**
     * (���v)<BR>
     */
    public String income;
    
    /**
     * (������)<BR>
     */
    public String closeMarginOrderNumber;

    /**
     * (����敪)<BR>
     * 1�F�[��i�[����{�����Ђ̗[�ꎞ�ԑт̂ݐݒ�j�@@NULL�F���̑�<BR>
     */
    public String sessionType;
}
@
