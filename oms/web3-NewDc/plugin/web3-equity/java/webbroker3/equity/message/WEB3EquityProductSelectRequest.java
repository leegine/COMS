head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityProductSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t���������I�����N�G�X�g(WEB3EquityProductSelectRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/10 �����a��(SRA) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�����������t���������I�����N�G�X�g�j�B<br>
 * <br>
 * �����������t���������I��v���@@���N�G�X�g�f�[�^�N���X
 * @@author �����a��(SRA) 
 * @@version 1.0
 */
public class WEB3EquityProductSelectRequest extends WEB3GenRequest {

    /**
     * �|�������t�B�b�N�^�C�v<BR>
     */
    public static final String PTYPE = "equity_productSelect";

    /**
     * �V���A���o�[�W����UID<BR>
     */
    public static final long serialVersionUID = 200412100000L;

    /**
     * ���X�|���X�f�[�^���쐬����B<BR>
     */
    public WEB3GenResponse createResponse() {
        return new WEB3EquityProductSelectResponse(this);
    }
    
    /**
     * �f�t�H���g�R���X�g���N�^�B<BR>
     */
    public WEB3EquityProductSelectRequest() {
    }

}
@
