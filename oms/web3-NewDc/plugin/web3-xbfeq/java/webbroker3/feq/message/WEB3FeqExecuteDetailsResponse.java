head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.36.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteDetailsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������ڍ׃��X�|���X(WEB3FeqExecuteDetailsResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O�������������ڍ׃��X�|���X)<BR>
 * �O�������������ڍ׃��X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqExecuteDetailsResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_executeDetails";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (�����ڍ�)<BR>
     * �����ڍ׃I�u�W�F�N�g<BR>
     */
    public WEB3FeqOrderDetailInfoUnit orderDetailInfoUnit;
    
    /**
     * (���ڍ�)<BR>
     * �O���������ڍ׏��I�u�W�F�N�g<BR>
     */
    public WEB3FeqExecuteDetailInfoUnit executeDetailInfoUnit;
    
    /**
     * @@roseuid 42CE3A0801C5
     */
    public WEB3FeqExecuteDetailsResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FeqExecuteDetailsResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
