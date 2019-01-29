head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.24.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductInformationUpdateDetailsInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������������X�V���ד��̓��X�|���X(WEB3AdminFeqProductInformationUpdateDetailsInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/27 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҊO�������������X�V���ד��̓��X�|���X)<BR>
 * �Ǘ��ҊO�������������X�V���ד��̓��X�|���X�N���X
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqProductInformationUpdateDetailsInputResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_feq_productInformationUpdateDetailsInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121526L;
        
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h
     */
    public String productCode;
    
    /**
     * (�������i�J�i�j)<BR>
     * �������i�J�i�j
     */
    public String productNameKana;
    
    /**
     * (�������i�����j)<BR>
     * �������i�����j
     */
    public String productNameKanji;
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h<BR>
     * <BR>
     * N1�F���`<BR>
     * N2�F�[�Z��<BR>
     * X1�F��C
     */
    public String marketCode;
    
    /**
     * (���t��~�敪)<BR>
     * ���t��~�敪<BR>
     * <BR>
     * 0�F���t�\<BR>
     * 1�F���t��~
     */
    public String buyStopDiv;
    
    /**
     * (���t��~�敪)<BR>
     * ���t��~�敪<BR>
     * <BR>
     * 0�F���t�\<BR>
     * 1�F���t��~
     */
    public String sellStopDiv;
    
    /**
     * (���n�����R�[�h)<BR>
     * ���n�����R�[�h
     */
    public String localProductCode;
    
    /**
     * (���t�P��)<BR>
     * ���t�P��
     */
    public String buyUnit;
    
    /**
     * (�Œᔃ�t�P��)<BR>
     * �Œᔃ�t�P��
     */
    public String minBuyUnit;
    
    /**
     * (���t�P��)<BR>
     * ���t�P��
     */
    public String sellUnit;
    
    /**
     * (�Œᔄ�t�P��)<BR>
     * �Œᔄ�t�P��
     */
    public String minSellUnit;
    
    /**
     * @@roseuid 42CE39FA01A5
     */
    public WEB3AdminFeqProductInformationUpdateDetailsInputResponse() 
    {
     
    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFeqProductInformationUpdateDetailsInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }    

}
@
