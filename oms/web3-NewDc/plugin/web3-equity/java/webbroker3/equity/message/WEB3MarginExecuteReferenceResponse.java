head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginExecuteReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p����������Ɖ�X�|���X�N���X(WEB3MarginExecuteReferenceResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 ������ (���u) �V�K�쐬
Revesion History : 2004/12/10 �K�� (SRA) �C��
*/
package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�M�p����������Ɖ�X�|���X�j�B<br>
 * <br>
 * �M�p����������Ɖ�X�|���X�N���X
 * @@version 1.0
 */
public class WEB3MarginExecuteReferenceResponse extends WEB3GenResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_executeReference";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    
    /**
     * (�����ꗗ)<BR>
     * <BR>
     * ���������\���p<BR>
     */
    public WEB3MarginProductCodeNameUnit[] productCodeNames;
    
    /**
     * (�s��R�[�h�ꗗ)<BR>
     */
    public String[] marketList;
    
    /**
     * (�������ꗗ)<BR>
     * �������ꗗ
     */
    public Date[] orderBizDateList;
      
    
    /**
     * (�������ꗗ)<BR>
     */
    public WEB3MarginExecuteGroup[] executeGroups;
    
    /**
     * (���y�[�W��)<BR>
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     */
    public String totalRecords;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     */
    public String pageIndex;
    
    /**
     * (ID�ꗗ)<BR>
     * ���������ɊY������S�����h�c<BR>
     * �i�\�[�g���ꂽ��ԁj<BR>
     * <BR>
     * �o�b�ł̏ꍇ�ݒ�<BR>
     */
    public String[] idList;
    
    /**
     * (����I���x��������\������s��R�[�h�̈ꗗ)<BR>
     */
    public String[] messageSuspension;
    
    /**
     * @@roseuid 414048CA02FC
     */
    public WEB3MarginExecuteReferenceResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^�B)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3MarginExecuteReferenceResponse(WEB3MarginExecuteReferenceRequest l_request)
    {
        super(l_request);
    }
}
@
