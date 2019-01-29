head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.29.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������Ɖ�X�|���X(WEB3FeqExecuteReferenceResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[   
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�O�������������Ɖ�X�|���X)<BR>
 * �O�������������Ɖ�X�|���X�N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqExecuteReferenceResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_executeReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (�������ꗗ)<BR>
     * �O�������������̔z��<BR>
     */
    public WEB3FeqProductCodeNameUnit[] productCodeNames;
    
    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h�̔z��<BR>
     */
    public String[] marketList;
    
    /**
     * (�������ꗗ)<BR>
     * �������̔z��<BR>
     */
    public Date[] orderBizDateList;
    
    /**
     * (�����ꗗ)<BR>
     * �O�������������ׂ̔z��<BR>
     */
    public WEB3FeqExecuteGroup[] executeGroups;
    
    /**
     * (����I���x���s��R�[�h�ꗗ)<BR>
     * ����I��������\������s��R�[�h�̈ꗗ<BR>
     */
    public String[] messageSuspension;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
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
     * @@roseuid 42CE3A09003E
     */
    public WEB3FeqExecuteReferenceResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3FeqExecuteReferenceResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    } 
}
@
