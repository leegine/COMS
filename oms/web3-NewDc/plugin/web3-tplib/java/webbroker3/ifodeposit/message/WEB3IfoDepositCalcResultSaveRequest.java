head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcResultSaveRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �؋����v�Z���ʕۑ����N�G�X�g�N���X(WEB3IfoDepositCalcResultSaveRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/08/22 ��(FLJ) �V�K�쐬
 */
package webbroker3.ifodeposit.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�؋����v�Z���ʕۑ����N�G�X�g)<BR>
 * �؋����v�Z���ʕۑ����N�G�X�g�N���X�B<BR>
 * 
 * @@author Sun (FLJ)
 */
public class WEB3IfoDepositCalcResultSaveRequest extends WEB3GenRequest
{
    
    public static final String PTYPE = "ifodeposit_calc_result_save";

    /**
     * �R���X�g���N�^�[
     */
    public WEB3IfoDepositCalcResultSaveRequest()
    {

    }
    
	/**
	 * �X���b�hNO 
	 */
	public	Long threadNo;
	
	/**
	 * �����J�nID
	 */
	public long fromAccountId;
	
	/**
	 * �����I��ID
	 */
	public long toAccountId;

    /**
     * @@return webbroker3.common.message.WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3IfoDepositCalcResultSaveResponse();
    }
}
@
