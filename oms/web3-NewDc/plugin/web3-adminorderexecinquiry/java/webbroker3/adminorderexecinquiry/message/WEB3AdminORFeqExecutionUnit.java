head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.45.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFeqExecutionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������o�����͖���(WEB3AdminORFeqExecutionUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�Ǘ��ҊO�������o�����͖���)<BR>
 * �Ǘ��ҊO�������o�����͖��׃N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminORFeqExecutionUnit extends Message
{
    
    /**
     * �����h�c
     */
    public String id;
    
    /**
     * (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     */
    public String managementCode;
    
    /**
     * (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     */
    public String requestNumber;
    
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (���n�����R�[�h)<BR>
     * ���n�����R�[�h<BR>
     */
    public String localProductCode;
    
    /**
     * (����敪)<BR>
     * ����敪<BR>
     * <BR>
     * 1�F�@@�������t����<BR>
     * 2�F�@@�������t����<BR>
     * 3�F�@@�V�K��������<BR>
     * 4�F�@@�V�K��������<BR>
     * 5�F�@@�����ԍϒ���<BR>
     * 6�F�@@�����ԍϒ���<BR>
     * 7�F�@@��������<BR>
     * 8�F�@@���n����<BR>
     * 99�F�@@����O����<BR>
     * 101�F�@@�~�j�����t����<BR>
     * 102�F�@@�~�j�����t����<BR>
     * 201�F�@@�����M�����t����<BR>
     * 202�F�@@�����M�����t����<BR>
     * 501�F�@@�ݓ����t����<BR>
     * 502�F�@@�ݓ����t����<BR>
     * 601�F�@@�w���敨�V�K��������<BR>
     * 602�F�@@�w���敨�V�K��������<BR>
     * 603�F�@@�w���敨�����ԍϒ���<BR>
     * 604�F�@@�w���敨�����ԍϒ���<BR>
     * 605�F�@@�w���I�v�V�����V�K��������<BR>
     * 606�F�@@�w���I�v�V�����V�K��������<BR>
     * 607�F�@@�w���I�v�V���������ԍϒ���<BR>
     * 608�F�@@�w���I�v�V���������ԍϒ���<BR>
     * 701�F  �O���������t<BR>�@@�@@�@@�@@�@@�@@�@@�@@�@@�@@
     * 702�F  �O���������t<BR>
     */
    public String tradingType;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date orderBizDate;
    
    /**
     * (���ԍ�)<BR>
     * ���ԍ�<BR>
     */
    public String execNo;
    
    /**
     * (���P��)<BR>
     * ���P��<BR>
     */
    public String execPrice;
    
    /**
     * (��萔��)<BR>
     * ��萔��<BR>
     */
    public String execQuantity;
    
    /**
     * (���ב�)<BR>
     * ���ב�<BR>
     */
    public String execExchangeRate;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public Date executionDate;
    
    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    public Date deliveryDate;
    
    /**
     * (���n��n��)<BR>
     * ���n��n��<BR>
     */
    public Date localDeliveryDate;
    
    /**
     * (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     */
    public String updaterCode;
    
    /**
     * (�Ǘ��ҊO�������o�����͖���)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 42AD03B30136
     */
    public WEB3AdminORFeqExecutionUnit() 
    {
     
    }
}
@
