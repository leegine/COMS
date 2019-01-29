head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.49.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	PointPremiumMasterDao.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.point.data;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.dbind.*;
import java.util.*;
import webbroker3.point.data.*;
import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/** 
 * {@@link PointPremiumMasterDao}��{@@link com.fitechlabs.xtrade.kernel.data.DataAccessObject}�̃T�u�N���X��{@@link PointPremiumMasterRow}�C���X�^���X�֊֘A�t���邱�Ƃ��ł��܂��B 
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
 * @@see PointPremiumMasterPK 
 * @@see PointPremiumMasterRow 
 */
public class PointPremiumMasterDao extends DataAccessObject {


  /** 
   * ����{@@link PointPremiumMasterDao}�Ɋ֘A����^�w���Row�I�u�W�F�N�g 
   */
    private PointPremiumMasterRow row;


  /** 
   * Row�I�u�W�F�N�g����V����DataAccessObject�I�u�W�F�N�g���쐬���邽�ߗ��p�����t�@@�N�g�� 
   */
    public static final Factory FACTORY = new Factory() {

        /** 
         * �w���{@@link PointPremiumMasterRow}�Ɖ��肳���{@@link DataAccessObject}����V����{@@link PointPremiumMasterDao}��Ԃ��܂��B 
         * @@return �w���Row�Ɍ��т�{@@link PointPremiumMasterDao}�C���X�^���X 
         * @@exception java.lang.IllegalArgumentException �w���Row�I�u�W�F�N�g��{@@link PointPremiumMasterRow}�̃^�C�v�ƈ�v���Ȃ��ꍇ 
         */
        public DataAccessObject newInstance( Row row ) {
            if ( row instanceof PointPremiumMasterRow )
                return new PointPremiumMasterDao( (PointPremiumMasterRow) row );
            throw new java.lang.IllegalArgumentException( "Not a PointPremiumMasterRow or subclass: "+row.getClass() );
        }
    };


  /** 
   * {@@link PointPremiumMasterRow}�������Ɏ��R���X�g���N�^�ł��B����̓t�@@�N�g������т��̃T�u�N���X�݂̂ɂ�藘�p����܂��B 
   * @@param row Dao�Ƀf�[�^��񋟂���{@@link PointPremiumMasterRow}�I�u�W�F�N�g 
    */
    protected PointPremiumMasterDao( PointPremiumMasterRow row ) {
        super( row );
        this.row = row;
    }


  /** 
   * ����Dao�Ɍ��т��Ă���{@@link PointPremiumMasterRow}�I�u�W�F�N�g���擾���܂��B
   */
    public PointPremiumMasterRow getPointPremiumMasterRow() {
        return row;
    }


  /** 
   * �w���{@@link PointPremiumMasterRow}�I�u�W�F�N�g����{@@link PointPremiumMasterDao}�I�u�W�F�N�g���쐬���܂��B 
   * ����͎��ۂ�{@@link PointPremiumMasterRow}�N���X�C���X�^���X���x�[�X�ɖ߂�l�Ƃ��ēK�؂�Dao�I�u�W�F�N�g�� 
   * �|�������t�B�b�N�ɍ쐬���܂��B 
   * 
   * @@param row �K�v��{@@link PointPremiumMasterDao}�擾�̂��߂Ɏw���{@@link PointPremiumMasterRow}�I�u�W�F�N�g 
   * @@return �w���row�I�u�W�F�N�g�ɑΉ�����{@@link PointPremiumMasterDao}�I�u�W�F�N�g 
   * @@exception java.lang.IllegalArgumentException �w���Row�^�C�v�ɑΉ�����Dao�̃^�C�v�����݂��Ȃ��ꍇ 
   */
    public static PointPremiumMasterDao forRow( PointPremiumMasterRow row ) throws java.lang.IllegalArgumentException {
        return (PointPremiumMasterDao) DataAccessObject.forRow( row );
    }


    //--------------------------------------------
    // Create new primary key values
    //--------------------------------------------


  /** 
   * {@@link PointPremiumMasterRow}����ӂɓ��肷��long�^�̒l�𐶐����܂��B 
   * ���̒l��{@@link PointPremiumMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �V����{@@link PointPremiumMasterPK}��f�[�^�x�[�X���R�[�h�Ƃ��đ}�������{@@link PointPremiumMasterParams}�C���X�^���X�̎�L�[�Ƃ��ė��p�\��long�^�̒l 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static long newPkValue() throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doGetNewPkValueQuery( PointPremiumMasterRow.TYPE );
    }


  /** 
   * {@@link PointPremiumMasterRow}����ӂɓ��肷��{@@link PointPremiumMasterPK}�I�u�W�F�N�g�𐶐����܂��B 
   * ���̃I�u�W�F�N�g��{@@link PointPremiumMasterRow}�̃I�u�W�F�N�g�^�C�v�ɑΉ�������̂ł��B 
   * 
   * @@return �f�[�^�x�[�X�֑}������V����{@@link PointPremiumMasterParams}�I�u�W�F�N�g�̎�L�[�Ƃ��ė��p�\��{@@link PointPremiumMasterPK}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception UnsupportedOperationException primary_key�ɕ����̃J�������܂܂�Ă��邩�J�����̃^�C�v��long�^�łȂ��ꍇ 
   */
    public static PointPremiumMasterPK newPkObject() throws DataNetworkException, DataQueryException {
      throw new java.lang.UnsupportedOperationException( "auto-generation of primary keys with multiple components not supported." );
    }


    //===========================================================================
    //
    // Find Rows by primary key
    //
    //===========================================================================


  /** 
   * �w��̎�L�[�̒l����{@@link PointPremiumMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_premiumNo �����Ώۂł���p_premiumNo�t�B�[���h�̒l
   * 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link PointPremiumMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static PointPremiumMasterRow findRowByPk( String p_institutionCode, String p_premiumNo ) throws DataFindException, DataQueryException, DataNetworkException {
        PointPremiumMasterPK pk = new PointPremiumMasterPK( p_institutionCode, p_premiumNo );
        return findRowByPk( pk );
    }


  /** 
   * �w���PointPremiumMasterPK�I�u�W�F�N�g����{@@link PointPremiumMasterRow}�I�u�W�F�N�g���������܂��B 
   * 
   * @@param pk �����L�[�Ƃ��ė��p����PointPremiumMasterPK�I�u�W�F�N�g 
   * @@return �����w���ID�ƈ�v�����L�[������{@@link PointPremiumMasterRow} 
   * @@exception DataFindException �N�G�����͎̂��s���ꂽ���A�w��̎�L�[�ɂČ����̃I�u�W�F�N�g��������Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�����͎̂��s���ꂽ���A�����L�[����ӂłȂ��Ȃǉ��炩�̃f�[�^�֘A�̗��R�Ō����Ɏ��s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public static PointPremiumMasterRow findRowByPk( PointPremiumMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return (PointPremiumMasterRow) qp.doFindByPrimaryKeyQuery( pk, null );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(String,String)}�����{@@link #forRow(PointPremiumMasterRow)}���g�p���Ă��������B 
   */
    public static PointPremiumMasterDao findDaoByPk( String p_institutionCode, String p_premiumNo ) throws DataFindException, DataQueryException, DataNetworkException {
        PointPremiumMasterPK pk = new PointPremiumMasterPK( p_institutionCode, p_premiumNo );
        PointPremiumMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


  /** 
   * @@deprecated �����{@@link #findRowByPk(PointPremiumMasterPK)}�����{@@link #forRow(PointPremiumMasterRow)}���g�p���Ă��������B 
   */
    public static PointPremiumMasterDao findDaoByPk( PointPremiumMasterPK pk ) throws DataFindException, DataQueryException, DataNetworkException {
        PointPremiumMasterRow row = findRowByPk( pk );
        return forRow( row );
    }


    //===========================================================================
    //
    // Fetch Rows related by foreign key
    //
    //===========================================================================


  /** 
   * ����{@@link PointPremiumMasterDao}�ɕR�t��{@@link PointPremiumMasterRow}���ŊO���L�[�̊֌W������{@@link PointCategoryMasterRow}���������܂��B 
   * 
   * @@return {@@link PointPremiumMasterDao}�ƊO���L�[�̊֌W�ɂ���{@@link PointCategoryMasterRow} 
   * @@exception DataFindException �O���L�[ID��null�łȂ��N�G�������s���ꂽ���A�f�[�^�x�[�X�ɊY���f�[�^�̑��݂��Ȃ��I�u�W�F�N�g�����Ɏ��s�����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   */
    public PointCategoryMasterRow fetchPointCategoryMasterRowViaInstitutionCodeCategoryNo() throws DataNetworkException, DataFindException, DataQueryException  {
        PointCategoryMasterPK pk = new PointCategoryMasterPK( row.getInstitutionCode(), row.getCategoryNo() );
        Row row = PointCategoryMasterDao.findRowByPk( pk );
        if ( row != null && !(row instanceof PointCategoryMasterRow) )
            throw new java.lang.IllegalStateException( "not a subclass: "+row.getClass() );
        return (PointCategoryMasterRow) row;
    }


  /** 
   * @@deprecated �����{@@link #fetchPointCategoryMasterRowViaInstitutionCodeCategoryNo()}�����{@@link #forRow(PointPremiumMasterRow)}���g�p���Ă��������B 
   */
    public PointCategoryMasterDao fetchPointCategoryMasterDaoViaInstitutionCodeCategoryNo() throws DataNetworkException, DataFindException, DataQueryException  {
        PointCategoryMasterPK pk = new PointCategoryMasterPK( row.getInstitutionCode(), row.getCategoryNo() );
        DataAccessObject dao = PointCategoryMasterDao.findDaoByPk( pk );
        if ( dao != null && !(dao instanceof PointCategoryMasterDao) )
            throw new java.lang.IllegalStateException( "not a subclass: "+dao.getClass() );
        return (PointCategoryMasterDao) dao;
    }


    //===========================================================================
    //
    // Find Rows given foreign key values
    //
    //===========================================================================

    //-----------------------------------------------------------
    // Find Rows given foreign key values for PointCategoryMaster
    //-----------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�����{@@link #findRowsByInstitutionCodeCategoryNo(PointCategoryMasterRow)}���g�p���Ă��������B 
   */
    public static List findRowsByInstitutionCodeCategoryNo( PointCategoryMasterDao dao ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionCodeCategoryNo( dao.getPointCategoryMasterRow() );
    }


  /** 
   * {@@link PointCategoryMasterRow}�ƊO���L�[�̊֌W�ɂ���{@@link PointPremiumMasterRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param row �O���L�[�̎Q�Ƃ�����{@@link PointCategoryMasterRow}�I�u�W�F�N�g 
   * @@return �w���{@@link PointCategoryMasterRow}�ɊO���L�[������{@@link PointPremiumMasterRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeCategoryNo( PointCategoryMasterRow row ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionCodeCategoryNo( row.getInstitutionCode(), row.getCategoryNo() );
    }


  /** 
   * {@@link PointCategoryMasterPK}�ƊO���L�[�̊֌W�ɂ���{@@link PointPremiumMasterRow}�I�u�W�F�N�g���Q�Ƃ�{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param pk �N�G�����ŗ��p����{@@link PointCategoryMasterPK}�I�u�W�F�N�g 
   * @@return {@@link PointCategoryMasterPK}�ƊO���L�[����v����l������{@@link PointPremiumMasterRow}��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeCategoryNo( PointCategoryMasterPK pk ) throws DataNetworkException, DataQueryException {
        return findRowsByInstitutionCodeCategoryNo( pk.institution_code, pk.category_no );
    }


  /** 
   * �w��̊O���L�[�̒l�i�ꍇ�ɂ�蕡���j����{@@link PointPremiumMasterRow}�I�u�W�F�N�g��������{@@link List}�Ƃ��ĕԂ��܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ�͋�̃��X�g��Ԃ��܂��B 
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_categoryNo �����Ώۂł���p_categoryNo�t�B�[���h�̒l
   * 
   * @@return �w��̊O���L�[������{@@link PointPremiumMasterRow}�I�u�W�F�N�g��{@@link List} 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static List findRowsByInstitutionCodeCategoryNo( String p_institutionCode, int p_categoryNo  ) throws DataNetworkException, DataQueryException {
        QueryProcessor qp = Processors.getDefaultProcessor();
        return qp.doFindAllQuery(
            PointPremiumMasterRow.TYPE,
            "institution_code=? and category_no=?",
            null,
            new Object[] { p_institutionCode, new Integer(p_categoryNo) } );
    }


    //-----------------------------------------------------------------
    // Find Daos given foreign key values for PointCategoryMaster
    //-----------------------------------------------------------------


  /** 
   * @@deprecated �����{@@link DataAccessObject#getRow()}�A{@@link #findRowsByInstitutionCodeCategoryNo(PointCategoryMasterRow)}�����{@@link #forRow(PointPremiumMasterRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeCategoryNo( PointCategoryMasterDao actor ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionCodeCategoryNo( actor ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeCategoryNo(PointCategoryMasterRow)}�����{@@link #forRow(PointPremiumMasterRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeCategoryNo( PointCategoryMasterRow row ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionCodeCategoryNo( row ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeCategoryNo(PointCategoryMasterPK)}�����{@@link #forRow(PointPremiumMasterRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeCategoryNo( PointCategoryMasterPK pk ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionCodeCategoryNo( pk.institution_code, pk.category_no ) );
    }


  /** 
   * @@deprecated �����{@@link #findRowsByInstitutionCodeCategoryNo(String, int)}�����{@@link #forRow(PointPremiumMasterRow)}���g�p���Ă��������B 
   */
    public static List findDaosByInstitutionCodeCategoryNo( String p_institutionCode, int p_categoryNo ) throws DataNetworkException, DataQueryException {
        return forRows( findRowsByInstitutionCodeCategoryNo( p_institutionCode, p_categoryNo ) );
    }



    //===========================================================================
    //
    // Find Rows or Daos given index values
    //
    //===========================================================================

    //------------------------------------------------------
    // Find Rows given unique index values
    //------------------------------------------------------


  /** 
   * p_institutionCode, p_premiumNo, and �ɂĎw��̒l�����ӂ�{@@link PointPremiumMasterRow}�I�u�W�F�N�g���������܂��B 
   * �Y������I�u�W�F�N�g���Ȃ��ꍇ��null��Ԃ��܂��B
   * 
   * @@param p_institutionCode �����Ώۂł���p_institutionCode�t�B�[���h�̒l
   * @@param p_premiumNo �����Ώۂł���p_premiumNo�t�B�[���h�̒l
   * 
   * @@return �����w���p_institutionCode, p_premiumNo, and �̒l�ƈ�v����{@@link PointPremiumMasterRow}�I�u�W�F�N�g 
   * @@exception DataNetworkException �l�b�g���[�N�܂��͂��̑��C���t���֌W�̏�Q�ŃN�G�������s�ł��Ȃ������ꍇ 
   * @@exception DataFindException �N�G�����s�ɐ������������s���ʂ𕡐��Ԃ����ꍇ 
   * @@exception DataQueryException �N�G�������s����Ă����炩�̃f�[�^�֘A�̗��R���玸�s�����ꍇ 
   */
    public static PointPremiumMasterRow findRowByInstitutionCodePremiumNo( String p_institutionCode, String p_premiumNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            PointPremiumMasterRow.TYPE,
            "institution_code=? and premium_no=?",
            null,
            new Object[] { p_institutionCode, p_premiumNo } );
        switch ( list.size() ) {
            case 0: return null;
            case 1: return (PointPremiumMasterRow) list.get(0);
            default: throw new DataFindException( "too many results in unique query PointPremiumMasterDao.findRowsByInstitutionCodePremiumNo(): "+list.size() );
        }
    }


  /** 
   * @@deprecated �����{@@link #findRowByInstitutionCodePremiumNo(String, String)}�����{@@link #forRow(PointPremiumMasterRow)}���g�p���Ă��������B 
   */
    public static PointPremiumMasterDao findDaoByInstitutionCodePremiumNo( String p_institutionCode, String p_premiumNo ) throws DataNetworkException, DataFindException, DataQueryException  {
        return forRow( findRowByInstitutionCodePremiumNo( p_institutionCode, p_premiumNo ) );
    }

    //------------------------------------------------------
    // Find Rows given non-unique index values
    //------------------------------------------------------

        // (none) 

}
@
