head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocumentDeliverResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���ʌ�t���X�|���X(WEB3DocumentDeliverResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/09/25 ���V��@@(SRA) �V�K�쐬
*/
package webbroker3.gentrade.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (���ʌ�t���X�|���X)<BR>
 * <BR>
 * ���ʌ�t���N�G�X�g<BR>
 * @@author ���V��@@
 * @@version 1.0
 */
public class WEB3DocumentDeliverResponse
	extends WEB3GentradeBatoDisplayCommonResponse 
{
    
    /**
    * PTYPE<BR>
    */
    public final static  String PTYPE = "document_deliver";
    
    /**
    * serialVersionUID<BR>
    */
    public final static long serialVersionUID = 200709281832L;
    
    /**
     * ��~�����s�ۃt���O<BR>
     * true�F���s�\<BR>
     * false�F���s�s�\<BR>
     */
    public boolean tradingStopFlag;
    
	/**
	 * �R���X�g���N�^<BR>
	 */
	public WEB3DocumentDeliverResponse() 
    {
	}

	/**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
	 * @@param l_request ���N�G�X�g�I�u�W�F�N�g
	 */
	public WEB3DocumentDeliverResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
	}

}
@
