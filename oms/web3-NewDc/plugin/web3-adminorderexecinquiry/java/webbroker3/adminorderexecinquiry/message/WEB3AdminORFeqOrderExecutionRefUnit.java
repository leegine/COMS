head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFeqOrderExecutionRefUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������������Ɖ�Unit(WEB3AdminORFeqOrderExecutionRefUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.adminorderexecinquiry.message;


/**
 * (�Ǘ��ҊO�������������Ɖ�Unit)
 * �Ǘ��ҊO�������������Ɖ�Unit�N���X
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminORFeqOrderExecutionRefUnit extends WEB3AdminOROrderExecutionRefCommon 
{
    
    /**
     * (�`�[No)<BR>
     * �`�[No<BR>
     */
    public String orderNumber;
    
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
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     */
    public String marketCode;
    
    /**
     * (�����敪)<BR>
     * 0�F��ʁ@@1�F����<BR>
     */
    public String taxType = null;
    
    /**
     * (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     */
    public String managementCode;
    
    /**
     * (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * <BR>
     * 0�F�~��<BR>
     * 1�F�O��<BR>
     */
    public String settleDiv;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;
    
    /**
     * (��n����i�O�݁j)<BR>
     * ��n����i�O�݁j<BR>
     */
    public String foreignDeliveryPrice = null;
    
    /**
     * (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     */
    public String updaterCode;
    
    /**
     * (�Ǘ��ҊO�������������Ɖ�Unit)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 42A69449009D
     */
    public WEB3AdminORFeqOrderExecutionRefUnit() 
    {
     
    }
}
@
