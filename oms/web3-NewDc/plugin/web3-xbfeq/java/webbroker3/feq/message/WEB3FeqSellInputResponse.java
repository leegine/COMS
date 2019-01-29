head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.24.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqSellInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O���������t���̓��X�|���X(WEB3FeqSellInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (�O���������t���̓��X�|���X)<BR>
 * �O���������t���̓��X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqSellInputResponse extends WEB3FeqInputCommonResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_sellInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (���ϋ敪�ꗗ)<BR>
     * ���ϋ敪�ꗗ<BR>
     * <BR>
     * 0�F�~��<BR>
     * 1�F�O��<BR>
     */
    public String[] settleDivList;
    
    /**
     * (�T�Z�뉿�P��)<BR>
     * �T�Z�뉿�P��<BR>
     * <BR>
     * ����������̏ꍇ�A�ݒ�<BR>
     */
    public String estimatedBookPrice;
    
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     * <BR>
     */
    public String productCode;
    
    /**
     * (���n�����R�[�h)<BR>
     * ���n�����R�[�h<BR>
     * <BR>
     */
    public String localProductCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     * <BR>
     */
    public String productName;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * N1�F���` ��C N2�F�[�Z�� X1�F��C<BR>
     * <BR>
     */
    public String marketCode;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     * <BR>
     */
    public String orderQuantity;
    
    /**
     * (��������敪)<BR>
     * ��������敪<BR>
     * 0�F��ʁ@@�@@1�F����<BR>
     * <BR>
     */
    public String taxType;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     * A0�FUSD A3�FHKD Z4�FEUR<BR>
     * �����P���敪���u�w�l�v�̏ꍇ�Z�b�g<BR>
     * <BR>
     */
    public String currencyCode;
    
    /**
     * @@roseuid 42CE3A030280
     */
    public WEB3FeqSellInputResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FeqSellInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
