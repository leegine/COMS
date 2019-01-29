head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����������Ɖ�X�|���X(WEB3MstkExecuteReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 �d��(���u) �V�K�쐬
                   2004/12/10 �K�� (SRA) �C��
                   2005/01/05 ���� (SRA) JavaDoc�C��
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�����~�j�����������Ɖ�X�|���X�j�B<BR>
 * <BR>
 * �����~�j�����������Ɖ�X�|���X�N���X
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3MstkExecuteReferenceResponse extends WEB3GenResponse 
{
    
    /**
     * �iPTYPE�j�B
     */
    public final static  String PTYPE = "mstk_executeReference";
        
    /**
     * �iSerialVersionUID�j�B
     */
    public final static long serialVersionUID = 200410101054L;    
    
    /**
     * �i�����ꗗ�j�B
     */
    public WEB3MstkProductCodeNameUnit[] productCodeNames;
    
    /**
     * �i�������ꗗ�j�B<BR>
     * <BR>
     * ���������ɕR�t�������������̈ꗗ<BR>
     * �i�����~�j����������薾�ׂ̔z��j
     */
    public WEB3MstkExecuteGroup[] executeGroups;
    
    /**
     * �i���y�[�W��)<BR>
     * <BR>
     * �Y������S�y�[�W��
     */
    public String totalPages;
    
    /**
     * �i�����R�[�h���j�B<BR>
     * <BR>
     * �Y������S�f�[�^��
     */
    public String totalRecords;
    
    /**
     * �i�\���y�[�W�ԍ��j�B<BR>
     * <BR>
     * ���ۂɕ\������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���
     */
    public String pageIndex;
    
    /**
     * �i����I���x���j�B<BR>
     * <BR>
     * true�F�x������\������@@�@@false�F�x������\�����Ȃ�
     */
    public boolean messageSuspensionFlag;
    
    /**
     * �i�����~�j�����������Ɖ�X�|���X�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     */
	public WEB3MstkExecuteReferenceResponse() 
	{

	}

    /**
     * �i�����~�j�����������Ɖ�X�|���X�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     * @@param l_request �����~�j�����������Ɖ�N�G�X�g
     */
    public WEB3MstkExecuteReferenceResponse(WEB3MstkExecuteReferenceRequest l_request) 
    {
        
        super(l_request);
         
    }
}
@
