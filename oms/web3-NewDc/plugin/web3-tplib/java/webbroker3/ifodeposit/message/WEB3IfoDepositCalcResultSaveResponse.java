head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.53.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcResultSaveResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �؋������DB�ۑ����X�|���X(WEB3IfoDepositCalcResultSaveResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/08/22 ��(FLJ) �V�K�쐬
 */
package webbroker3.ifodeposit.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�؋������DB�ۑ����X�|���X)<BR>
 * �؋������DB�ۑ����X�|���X�N���X�B<BR>
 * 
 * @@author Sun (FLJ)
 */
public class WEB3IfoDepositCalcResultSaveResponse extends WEB3GenResponse
{
    
    public static final String PTYPE = "ifodeposit_calc_result_save";

    /**
     * �R���X�g���N�^�[
     */
    public WEB3IfoDepositCalcResultSaveResponse()
    {

    }
    
	/**
	 * �X���b�hNO 
	 */
	public	Long threadNo;
	
    /**
     * (������)
     */
    public Long success;

    /**
     * (���s��)
     */
    public Long failure;


}
@
