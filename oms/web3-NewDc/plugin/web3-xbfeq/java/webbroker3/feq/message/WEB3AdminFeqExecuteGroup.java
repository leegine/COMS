head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.27.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecuteGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������ׁi�Ǘ��ҁj(WEB3AdminFeqExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 �s�p (���u) �V�K�쐬
                 : 2005/08/02 ���U (���u) ���r���[
*/


package webbroker3.feq.message;


/**
 * (�O�������������ׁi�Ǘ��ҁj)<BR>
 * �O�������������ׁi�Ǘ��ҁj�N���X
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminFeqExecuteGroup extends WEB3FeqOrderCommonUnit 
{    
    /**
     * (��������ԍ�)<BR>
     * ��������ԍ�<BR>
     * �i�����ԍ��j
     */
    public String orderActionId;
    
    /**
     * (��萔��)<BR>
     * ��萔��<BR>
     * <BR>
     * �����o�������̏ꍇ�́Anull�B
     */
    public String execQuantity;
    
    /**
     * (��n���)<BR>
     * ��n���
     */
    public String deliveryPrice;
    
    /**
     * (��n����i�O�݁j)<BR>
     * ��n����i�O�݁j
     */
    public String foreignDeliveryPrice;
    
    /**
     * (������)<BR>
     * ������
     */
    public String transactionStateType;
    
    /**
     * (�O�������������ׁi�Ǘ��ҁj)<BR>
     * �R���X�g���N�^
     * @@roseuid 4201EF9D02F3
     */
    public WEB3AdminFeqExecuteGroup() 
    {
     
    }
}
@
