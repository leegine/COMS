head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.30.05.56.57;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d92c6286688;
filename	WEB3FPTDocumentGetResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (��)��a�����r�W�l�X�E�C�m�x�[�V����
 File Name           : �����@@���ʏ��擾���X�|���X�N���X(WEB3FPTDocumentGetResponse.java)
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/17 �����C(�k�����u) �V�K�쐬 �d�l�ύX���f��No.354
 */
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����@@���ʏ��擾���X�|���X)<BR>
 * �����@@���ʏ��擾���X�|���X�N���X<BR>
 * <BR>
 * @@author �����C(�k�����u)
 * @@version 1.0
 */
public class WEB3FPTDocumentGetResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "fpt_document_get";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 201011170922L;

    /**
     * (���ʏ��)<BR>
     * ���ʏ��<BR>
     */
    public WEB3FPTDocumentInfoUnit[] documentList;

    /**
     * �R���X�g���N�^<BR>
     */
    public WEB3FPTDocumentGetResponse()
    {

    }

    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FPTDocumentGetResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
