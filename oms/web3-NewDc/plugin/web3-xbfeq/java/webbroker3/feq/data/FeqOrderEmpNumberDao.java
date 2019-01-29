head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	FeqOrderEmpNumberDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.feq.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.*;

/** 
 * {@@link FeqOrderEmpNumberDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link FeqOrderEmpNumberRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
 * �N���C�A���g�R�[�h�ɂ����ĕK�v�Ƃ���鋤�ʂ̃f�[�^�I�y���[�V�������������Ă��܂��B 
 * <p> 
 *     <li> �V�������R�[�h�ɑ΂���ӂ̎�L�[�l�܂��̓I�u�W�F�N�g���쐬 </li> 
 *     <li> �O���L�[���烌�R�[�h������ </li> 
 *     <li> �O���L�[�̊֌W�ɂ���I�u�W�F�N�g������ </li> 
 *     <li> �C���f�b�N�X���������̃f�[�^�x�[�X�X�L�[�}�ɃN�G�������s </li> 
 * <p> 
 * 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.xtrade.kernel.data.DataAccessObject 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see FeqOrderEmpNumberPK 
 * @@see FeqOrderEmpNumberRow 
 */
public class FeqOrderEmpNumberDao extends DataAccessObject {


  /** 
   * ����{@@link FeqOrderEmpNumberDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private FeqOrderEmpNumberRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link FeqOrderEmpNumberRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link FeqOrderEmpNumberDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link FeqOrderEmpNumberDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link FeqOrderEmpNumberRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof FeqOrderEmpNumberRow )
                return new FeqOrderEmpNumberDao( (FeqOrderEmpNumberRow) row );
            throw new java.lang.IllegalArgumentException( "Not a FeqOrderEmpNumberRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link FeqOrderEmpNumberRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link FeqOrderEmpNumberRow}�I�u�W�F�N�g 
    */
    protected FeqOrderEmpNumberDao( FeqOrderEmpNumberRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link FeqOrderEmpNumberRow}�I�u�W�F�N�g���擾���܂��B
   */
    public FeqOrderEmpNumberRow getFeqOrderEmpNumberRow() {
        return row;
    }


  /** 
   * �w���{@@link FeqOrderEmpNumberRow}�I�u�W�F�N�g����{@@link FeqOrderEmpNumberDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link FeqOrderEmpNumberRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link FeqOrderEmpNumberDao}�擾�̂��߂Ɏw���{@@link FeqOrderEmpNumberRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link FeqOrderEmpNumberDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static FeqOrderEmpNumberDao forRow( FeqOrderEmpNumberRow row ) throws java.lang.IllegalArgumentException {
        return (FeqOrderEmpNumberDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link FeqOrderEmpNumberRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link FeqOrderEmpNumberRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link FeqOrderEmpNumberPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link FeqOrderEmpNumberParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( FeqOrderEmpNumberRow.TYPE );
    }


  /** 
   * {@@link FeqOrderEmpNumberRow}����ӂɓ��肷��{@@link FeqOrderEmpNumberPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link FeqOrderEmpNumberRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link FeqOrderEmpNumberParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link FeqOrderEmpNumberPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static FeqOrderEmpNumberPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link FeqOrderEmpNumberRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_feqOrderEmpDiv �����Ώۂł���p_feqOrderEmpDiv�t�B�[���h�̒l
   * @@param p_bizDate �����Ώۂł���p_bizDate�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FeqOrderEmpNumberRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FeqOrderEmpNumberRow findRowByPk( String p_institutionCode, String p_feqOrderEmpDiv, java.sql.Timestamp p_bizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderEmpNumberPK pk = new FeqOrderEmpNumberPK( p_institutionCode, p_feqOrderEmpDiv, p_bizDate );
        return findRowByPk( pk );
    }


  /** 
   * �w���FeqOrderEmpNumberPK�I�u�W�F�N�g����{@@link FeqOrderEmpNumberRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����FeqOrderEmpNumberPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link FeqOrderEmpNumberRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static FeqOrderEmpNumberRow findRowByPk( FeqOrderEmpNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (FeqOrderEmpNumberRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String,java.sql.Timestamp)}�����{@@link #forRow(FeqOrderEmpNumberRow)}���g�p���Ă��������B 
   */
    public static FeqOrderEmpNumberDao findDaoByPk( String p_institutionCode, String p_feqOrderEmpDiv, java.sql.Timestamp p_bizDate ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderEmpNumberPK pk = new FeqOrderEmpNumberPK( p_institutionCode, p_feqOrderEmpDiv, p_bizDate );
        FeqOrderEmpNumberRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(FeqOrderEmpNumberPK)}�����{@@link #forRow(FeqOrderEmpNumberRow)}���g�p���Ă��������B 
   */
    public static FeqOrderEmpNumberDao findDaoByPk( FeqOrderEmpNumberPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        FeqOrderEmpNumberRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


      // (this object has no foreign keys)


    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_feqOrderEmpDiv, p_bizDate, and �ɂĎw��̒l�����ӂ�{@@link FeqOrderEmpNumberRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_feqOrderEmpDiv �����Ώۂł���p_feqOrderEmpDiv�t�B�[���h�̒l
   * @@param p_bizDate �����Ώۂł���p_bizDate�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_feqOrderEmpDiv, p_bizDate, and �̒l�ƈ�v����{@@link FeqOrderEmpNumberRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static FeqOrderEmpNumberRow findRowByInstitutionCodeFeqOrderEmpDivBizDate( String p_institutionCode, String p_feqOrderEmpDiv, java.sql.Timestamp p_bizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            FeqOrderEmpNumberRow.TYPE,
            "institution_code=? and feq_order_emp_div=? and biz_date=?",
            null,
            new Object[] { p_institutionCode, p_feqOrderEmpDiv, p_bizDate } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (FeqOrderEmpNumberRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query FeqOrderEmpNumberDao.findRowsByInstitutionCodeFeqOrderEmpDivBizDate(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodeFeqOrderEmpDivBizDate(String, String, java.sql.Timestamp)}�����{@@link #forRow(FeqOrderEmpNumberRow)}���g�p���Ă��������B 
   */
    public static FeqOrderEmpNumberDao findDaoByInstitutionCodeFeqOrderEmpDivBizDate( String p_institutionCode, String p_feqOrderEmpDiv, java.sql.Timestamp p_bizDate ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodeFeqOrderEmpDivBizDate( p_institutionCode, p_feqOrderEmpDiv, p_bizDate ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
