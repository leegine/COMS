head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORExecutionNumberInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE������茏���Ɖ���̓��X�|���X (WEB3AdminORExecutionNumberInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/09/11 ꎉ� (���u) �d�l�ύXNo.55�C��
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE������茏���Ɖ���̓��X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE������茏���Ɖ���̓��X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminORExecutionNumberInputResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORExecutionNumberInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_execution_number_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * (�N���ꗗ)<BR>
     * <BR>
     * �N���ꗗ<BR>
     * (YYYYMM)<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * searchYmList<BR>
     * (YYYYMM)<BR>
     * <BR>
     */
    public String[] searchYmList;

    /**
     * (�M�p���{�t���O)<BR>
     * <BR>
     * �M�p���{�t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * marginFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean marginFlag;

    /**
     * (�~�j�����{�t���O)<BR>
     * <BR>
     * �~�j�����{�t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * miniFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean miniFlag;

    /**
     * (�O�����{�t���O)<BR>
     * <BR>
     * �O�����{�t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * foreignFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean foreignFlag;

    /**
     * (�I�v�V�������{�t���O)<BR>
     * <BR>
     * �I�v�V�������{�t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * optionFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean optionFlag;

    /**
     * (�敨���{�t���O)<BR>
     * <BR>
     * �敨���{�t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * futureFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean futureFlag;

    /**
     * (���M���{�t���O)<BR>
     * <BR>
     * ���M���{�t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * mutualFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean mutualFlag;

    /**
     * �ݓ����{�t���O
     *
     * false�F�@@�����{
     * true�F�@@���{
     *
     * (�ݓ����{�t���O)<BR>
     * <BR>
     * �ݓ����{�t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * ruitoFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean ruitoFlag;
    
    /**
     * (�����{�t���O)<BR>
     * <BR>
     * �����{�t���O<BR>
     * <BR>
     * false�F�@@�����{<BR>
     * true�F�@@���{<BR>
     * <BR>
     * -----<English>---------------<BR>
     * <BR>
     * bondFlag<BR>
     * <BR>
     * false: FALSE<BR>
     * true: TRUE<BR>
     * <BR>
     */
    public boolean bondFlag;


    /**
     * @@roseuid 4212FD0C03D0
     */
    public WEB3AdminORExecutionNumberInputResponse()
    {

    }

    /**
     * @@param l_request WEB3GenRequest
     */
    public WEB3AdminORExecutionNumberInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
