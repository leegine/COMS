head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecDetailInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������ڍׁi�Ǘ��ҁj(WEB3FeqExecDetailInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/
package webbroker3.feq.message;

import java.util.Date;


/**
 * (�O���������ڍׁi�Ǘ��ҁj)<BR>
 * �O���������ڍׁi�Ǘ��ҁj�N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3FeqExecDetailInfoUnit extends WEB3FeqExecuteUnit 
{
    
    /**
     * (���ID)<BR>
     * ���ID
     */
    public String execId;
    
    /**
     * (���ԍ�)<BR>
     * ���ԍ�
     */
    public String execNo;
    
    /**
     * (�������)<BR>
     * �������
     */
    public String foreignTradePrice;
    
    /**
     * (���n�萔��)<BR>
     * ���n�萔��
     */
    public String localCommission;
    
    /**
     * (���n�����)<BR>
     * ���n�����
     */
    public String localTradingTax;
    
    /**
     * (���̑��R�X�g1)<BR>
     * ���̑��R�X�g1
     */
    public String otherCost1;
    
    /**
     * (���̑��R�X�g2)<BR>
     * ���̑��R�X�g2
     */
    public String otherCost2;
    
    /**
     * (���Z����i�~�݁j)<BR>
     * ���Z����i�~�݁j
     */
    public String clearUpPrice;
    
    /**
     * (���Z����i�O�݁j)<BR>
     * ���Z����i�O�݁j
     */
    public String foreignClearUpPrice;
    
    /**
     * (�����萔���i�~�݁j)<BR>
     * �����萔���i�~�݁j
     */
    public String commission;
    
    /**
     * (�����萔���i�O�݁j)<BR>
     * �����萔���i�O�݁j
     */
    public String foreignCommission;
    
    /**
     * (����Łi�~�݁j)<BR>
     * ����Łi�~�݁j
     */
    public String consumptionTax;
    
    /**
     * (����Łi�O�݁j)<BR>
     * ����Łi�O�݁j
     */
    public String foreignConsumptionTax;
    
    /**
     * (��n���z�i�~�݁j)<BR>
     * ��n���z�i�~�݁j
     */
    public String deliveryPrice;
    
    /**
     * (��n���z�i�O�݁j)<BR>
     * ��n���z�i�O�݁j
     */
    public String foreignDeliveryPrice;
    
    /**
     * (������n��)<BR>
     * ������n��
     */
    public Date deliveryDate;
    
    /**
     * (�O���������ڍׁi�Ǘ��ҁj)<BR>
     * �R���X�g���N�^
     * @@roseuid 4281E60D03A8
     */
    public WEB3FeqExecDetailInfoUnit() 
    {
     
    }
}
@
