head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3BVSBookValueSpecsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �뉿�P�����׏Ɖ�X�|���X(WEB3BVSBookValueSpecsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/08  �Ɍ��t(���u) �V�K�쐬
*/
package webbroker3.tradehistory.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�뉿�P�����׏Ɖ�X�|���X)<BR>
 * �뉿�P�����׏Ɖ�X�|���X�N���X<BR>
 * 
 * @@author �Ɍ��t
 * @@version 1.0  
 */
public class WEB3BVSBookValueSpecsResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PLSBVS_bookValueSpecs";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411051040L;
        
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;
    
    /**
     * (���݂̑��v)<BR>
     * ���݂̑��v<BR>
     */
    public String currentProlossAmount;
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * (�뉿�P�����׏��ꗗ)<BR>
     */
    public WEB3BVSBookValueSpecsUnit[] bookValueUnits;
    
    /**
     * @@roseuid 418877BB03D8
     */
    public WEB3BVSBookValueSpecsResponse() 
    {
     
    }
    
    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3BVSBookValueSpecsResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
